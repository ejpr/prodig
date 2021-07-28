package com.challenge.prodig.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus status;

    private int code;

    private List<String> errors;


    public static ErrorResponse makeErrorResponse(HttpStatus httpStatus, List<String> errors){
       // @formatter:off
        return ErrorResponse.builder()
                            .code(httpStatus.value())
                            .status(httpStatus)
                            .errors(errors)
                            .build();
       // @formatter:on

    }

}