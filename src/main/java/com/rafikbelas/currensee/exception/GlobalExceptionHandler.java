package com.rafikbelas.currensee.exception;

import com.cloudmersive.client.invoker.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";

    @ExceptionHandler(InvalidVatException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handeInvalidVatException(InvalidVatException ex) {
        return new ErrorResponse(NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handeApiException(ApiException ex) {
        ex.printStackTrace();
        return new ErrorResponse(INTERNAL_SERVER_ERROR.value(), UNEXPECTED_ERROR);
    }

}
