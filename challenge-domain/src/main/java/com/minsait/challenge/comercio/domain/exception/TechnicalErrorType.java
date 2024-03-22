package com.minsait.challenge.comercio.domain.exception;

public enum TechnicalErrorType implements ErrorType {
    INTERNAL_SERVER_ERROR(500, 500, "Internal Server Error");

    private final Integer code;
    private final Integer httpStatus;
    private final String description;

    TechnicalErrorType(Integer code, Integer httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public Integer getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
