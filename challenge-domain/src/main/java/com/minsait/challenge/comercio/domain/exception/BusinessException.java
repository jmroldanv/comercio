package com.minsait.challenge.comercio.domain.exception;

public class BusinessException extends DomainException {
    public BusinessException(BusinessErrorType errorType, String... args) {
        super(errorType, args);
    }
}
