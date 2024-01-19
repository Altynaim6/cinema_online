package com.example.demo.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(com.example.demo.exception.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public com.example.demo.exception.ExceptionResponse handlerNotFoundException(NotFoundException e) {
        return new com.example.demo.exception.ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage());
    }

    @ExceptionHandler(com.example.demo.exception.BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public com.example.demo.exception.ExceptionResponse handlerNotFoundException(com.example.demo.exception.BadCredentialsException e) {

        return new com.example.demo.exception.ExceptionResponse(
                HttpStatus.FORBIDDEN,
                e.getMessage());
    }

    @ExceptionHandler(com.example.demo.exception.BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.example.demo.exception.ExceptionResponse handlerNotFoundException(com.example.demo.exception.BadRequestException e) {

        return new com.example.demo.exception.ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage());
    }

    @ExceptionHandler(com.example.demo.exception.BlockedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public com.example.demo.exception.ExceptionResponse handleBlockedException(com.example.demo.exception.BlockedException e) {
        return new com.example.demo.exception.ExceptionResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

}