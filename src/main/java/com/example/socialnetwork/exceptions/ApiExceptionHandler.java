package com.example.socialnetwork.exceptions;

public class ApiExceptionHandler extends RuntimeException {
    public ApiExceptionHandler() {
        super();
    }

    public ApiExceptionHandler(String message) {
        super(message);
    }
}
