package com.minsait.challenge.comercio.domain.exception;

public class TechnicalException extends DomainException {
    public TechnicalException(TechnicalErrorType errorType, String... args) {
        super(errorType, args);
    }
}
