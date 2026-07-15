package com.example.customerservice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String errors;
    private LocalDateTime timestamp;
}
