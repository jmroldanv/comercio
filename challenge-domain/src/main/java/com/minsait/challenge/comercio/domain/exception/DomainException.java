package com.minsait.challenge.comercio.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainException extends RuntimeException {

    @Getter private final ErrorType errorType;
    private final String[] args;

    public Integer getCode() {
        return this.errorType.getCode();
    }

    public Integer getHttpStatus() {
        return this.errorType.getHttpStatus();
    }

    public String getErrorName() {
        return this.errorType.getName();
    }

    public String getDescription() {
        return String.format(this.errorType.getDescription(), this.args);
    }
}
