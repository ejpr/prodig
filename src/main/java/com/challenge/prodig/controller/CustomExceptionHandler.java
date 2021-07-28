package com.challenge.prodig.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.challenge.prodig.dto.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected static ResponseEntity<Object> handleArgumentNotValidException(MethodArgumentNotValidException ex) {

        // @formatter:off
        List<String> errorList = ex
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());
        // @formatter:on

        ErrorResponse errorResponse = ErrorResponse.makeErrorResponse(HttpStatus.BAD_REQUEST, errorList);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
