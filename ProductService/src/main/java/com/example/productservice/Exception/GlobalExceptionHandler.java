package com.example.productservice.Exception;

import com.example.productservice.DTO.Response.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    ) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiResponseError response = new ApiResponseError();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setErrors(HttpStatus.BAD_REQUEST.name());
        response.setTimestamp(LocalDateTime.now());
        response.setMessage("Không thể hoàn thành yêu cầu");

        response.setValidationErrors(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
