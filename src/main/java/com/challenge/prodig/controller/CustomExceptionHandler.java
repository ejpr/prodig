package com.challenge.prodig.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.challenge.prodig.dto.ErrorResponse;
import com.challenge.prodig.exception.PersonNotFoundException;

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

    @ExceptionHandler(PersonNotFoundException.class)
    protected static ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException ex) {

        // @formatter:off
        List<String> errorList = Collections.singletonList(ex.getMessage());
        // @formatter:on

        ErrorResponse errorResponse = ErrorResponse.makeErrorResponse(HttpStatus.CONFLICT, errorList);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(Exception.class)
    protected static ResponseEntity<Object> handleGeneralException(Exception ex) {

        // @formatter:off
        List<String> errorList = Collections.singletonList("Generic error");
        // @formatter:on

        ErrorResponse errorResponse = ErrorResponse.makeErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorList);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
