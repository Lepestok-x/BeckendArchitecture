package com.example.Spring.exception_class;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
    private final HttpStatus status;
    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus status() {
        return status;
    }
}
