package com.example.orderservice.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseError {
    private LocalDateTime timestamp;
    private int status;
    private String errors;
    private String message;

    private Map<String, String> validationErrors;
}
