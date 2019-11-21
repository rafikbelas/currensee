package com.rafikbelas.currensee.exception;

import com.cloudmersive.client.invoker.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";

    @ExceptionHandler(InvalidVatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handeInvalidVatException(InvalidVatException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handeApiException(ApiException ex) {
        ex.printStackTrace();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), UNEXPECTED_ERROR);
    }

}
