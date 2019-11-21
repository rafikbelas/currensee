package com.rafikbelas.currensee.exception;

import com.cloudmersive.client.invoker.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidVatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handeInvalidVatException(InvalidVatException ex) {
        return new StringBuilder().append((HttpStatus.NOT_FOUND.value()))
                .append(", ")
                .append(ex.getMessage()).toString();
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handeApiException(ApiException ex) {
        ex.printStackTrace();
        return new StringBuilder().append((HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .append(", ")
                .append(ex.getMessage()).toString();
    }

}
