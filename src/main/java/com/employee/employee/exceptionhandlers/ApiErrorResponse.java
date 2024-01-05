package com.employee.employee.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

//    private String errorCode;
//
//    private String errorMessage;
//
//    public ApiErrorResponse(String errorCode) {
//        this.errorCode = errorCode;
//
//
//    }

    private String errorCode;
    private String errorMessage;
    private String timestamp; // Added for informative response

    // Constructor for setting errorCode and timestamp
    public ApiErrorResponse(String errorCode) {
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now().toString(); // Set current timestamp
    }


    public ApiErrorResponse(String internalServerError, String message) {
    }
}
