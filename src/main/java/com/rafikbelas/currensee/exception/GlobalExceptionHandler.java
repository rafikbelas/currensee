package com.rafikbelas.currensee.exception;

import com.cloudmersive.client.invoker.ApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

import java.net.UnknownHostException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";

    @ExceptionHandler(InvalidVatException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiError handeInvalidVatException(InvalidVatException ex) {
        String message = String.format("The parameter VAT is not a valid VAT number.");
        return new ApiError(NOT_FOUND, message, ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiError handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, "Validation error");
        apiError.addValidationErrors(ex.getConstraintViolations());
        return apiError;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiError handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        return new ApiError(BAD_REQUEST, message, ex);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiError handeApiException(ApiException ex) {
        return new ApiError(INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR);
    }

    @ExceptionHandler(UnknownHostException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiError handleUnknownHostException(UnknownHostException ex) {
        return new ApiError(INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR);
    }
}
