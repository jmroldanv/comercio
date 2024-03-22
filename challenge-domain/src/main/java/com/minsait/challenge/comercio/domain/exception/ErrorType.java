package com.minsait.challenge.comercio.domain.exception;

public interface ErrorType {
    Integer getCode();

    Integer getHttpStatus();

    String getName();

    String getDescription();
}
