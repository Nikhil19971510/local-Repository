package com.springboot.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class APIResponse {

    private String message;
    private int status;
    private Object object;

    // Custom constructor with message and HttpStatus
    public APIResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
    }

    // Custom constructor with message, HttpStatus, and object
    public APIResponse(String message, HttpStatus httpStatus, Object object) {
        this.message = message;
        this.status = httpStatus.value();
        this.object = object;
    }

    // Custom constructor with message, int status and object (already covered by @AllArgsConstructor, but here explicitly)
    public APIResponse(String message, int status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }
}

