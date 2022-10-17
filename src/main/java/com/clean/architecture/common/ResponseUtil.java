package com.clean.architecture.common;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ResponseUtil {

    public static <T>ResponseEntity<Object> build(String message, T data, HttpStatus httpStatus){
        return new ResponseEntity<>(build(message, data), httpStatus);
    }

    public static <T> ApiResponse<T> build(String message, T data){
        return ApiResponse.<T>builder()
                .status(ApiResponseStatus.builder()
                        .message(message)
                        .build())
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }
}
