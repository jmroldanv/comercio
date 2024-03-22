package com.minsait.challenge.comercio.domain.exception;

public enum BusinessErrorType implements ErrorType {
    NOT_FOUND(1000, 404, "Element not found"),
    VALIDATION_ERROR(1001, 400, "Validation error");

    private final Integer code;
    private final Integer httpStatus;
    private final String description;

    BusinessErrorType(Integer code, Integer httpStatus, String description) {
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
