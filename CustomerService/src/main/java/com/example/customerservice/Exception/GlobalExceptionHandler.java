package com.example.customerservice.Exception;

import com.example.customerservice.DTO.response.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleResourceNotFound(
            ResourceNotFoundException exception
    ) {

        ApiResponseError response = new ApiResponseError();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setErrors(HttpStatus.NOT_FOUND.name());
        response.setTimestamp(LocalDateTime.now());
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
