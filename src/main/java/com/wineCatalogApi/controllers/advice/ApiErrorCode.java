package com.wineCatalogApi.controllers.advice;

public enum ApiErrorCode {

    VALIDATION_ERROR("VALIDATION");

    private String code;

    ApiErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
