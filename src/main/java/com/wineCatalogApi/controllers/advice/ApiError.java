package com.wineCatalogApi.controllers.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private String code;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> errors;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }


    public ApiError(String code, String message, Throwable ex) {
        this();
        this.code = code;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private void addSubError(ApiSubError subError) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(subError);
    }

    private void addValidationError(String field, Object rejectedValue, String message) {
        addSubError(new ApiSubError(field, rejectedValue, message));
    }

    private void addValidationError(String message) {
        addSubError(new ApiSubError(message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getDefaultMessage());
    }

    void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public List<ApiSubError> getErrors() {
        return errors;
    }

    class ApiSubError {
        private String field;
        private Object rejectedValue;
        private String message;

        ApiSubError(String message) {
            this.message = message;
        }

        public ApiSubError(String field, Object rejectedValue, String message) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public String getMessage() {
            return message;
        }

    }
}