package com.example.Spring.advice_exception;

import com.example.Spring.body_error.ErrorResponse;
import com.example.Spring.exception_class.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> errorNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now(),
            ex.status().value(),
            ex.getMessage(),
            "Ошибка, такого студента не существует.",
            request.getRequestURI()
        );
        return ResponseEntity.status(ex.status()).body(errorResponse);
    }
}
