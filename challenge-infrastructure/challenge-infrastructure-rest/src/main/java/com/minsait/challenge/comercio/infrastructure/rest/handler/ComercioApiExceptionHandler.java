package com.minsait.challenge.comercio.infrastructure.rest.handler;

import com.minsait.challenge.comercio.domain.exception.BusinessErrorType;
import com.minsait.challenge.comercio.domain.exception.DomainException;
import com.minsait.challenge.comercio.infrastructure.rest.apimodel.model.ErrorDto;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ComercioApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {
        log.error("DomainException: {}", ex.getDescription());
        return createResponseEntity(ex.getHttpStatus(), createError(ex, request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        return createResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                createError(500, "Excepción genérica", ex.getMessage(), request.getContextPath()));
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handleValidationException(
            ValidationException ex, WebRequest request) {
        return createResponseEntity(
                HttpStatus.BAD_REQUEST.value(),
                createError(
                        BusinessErrorType.VALIDATION_ERROR.getCode(),
                        ex.getMessage(),
                        ex.getMessage(),
                        request.getContextPath()));
    }

    private ErrorDto createError(DomainException ex, WebRequest request) {
        return createError(
                ex.getCode(), ex.getErrorName(), ex.getDescription(), request.getDescription(true));
    }

    private ErrorDto createError(Integer status, String title, String detail, String instance) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(status);
        errorDto.setTitle(title);
        errorDto.setDetail(detail);
        errorDto.setInstance(instance);
        return errorDto;
    }

    private ResponseEntity<Object> createResponseEntity(Integer status, ErrorDto errorDto) {
        return ResponseEntity.status(status)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE)
                .body(errorDto);
    }
}
