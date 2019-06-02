package com.wineCatalogApi.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class RestExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed or invalid.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ApiError handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Malformed JSON request";
        log.error(ex.getMessage(), ex);
        return new ApiError(ApiErrorCode.VALIDATION_ERROR.getCode(), error, ex);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError();
        apiError.setCode(ApiErrorCode.VALIDATION_ERROR.getCode());
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        log.error(ex.getMessage(), ex);
        return apiError;
    }

    /**
     * Handle ConstraintViolationException. Triggered when an object fails @Validated validation.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ApiError handleConstraintViolationException(ConstraintViolationException ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage("Validation error");
        apiError.setCode(ApiErrorCode.VALIDATION_ERROR.getCode());
        List<FieldError> fieldErrors = new ArrayList<>();
        ex.getConstraintViolations().stream().forEach(cv -> fieldErrors.add(new FieldError("", cv.getPropertyPath().toString(), cv.getMessage())));
        apiError.addValidationErrors(fieldErrors);
        log.error(ex.getMessage(), ex);
        return apiError;
    }

    /**
     * Handle ConstraintViolationException. Triggered when an object fails @Validated validation.
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ApiError handleValidationException(ValidationException ex) {
        log.error(ex.getMessage(), ex);
        return new ApiError(ApiErrorCode.VALIDATION_ERROR.getCode(), ex.getMessage(), ex);
    }

    /**
     * Handle InvalidArgumentException. Triggered when an object fails his custom validation.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ApiError handleInvalidRequest(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return new ApiError(ApiErrorCode.VALIDATION_ERROR.getCode(), ex.getMessage(), ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    protected ApiError handleUncaughtException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ApiError(INTERNAL_SERVER_ERROR.name(), ex.getMessage(), ex);
    }
}