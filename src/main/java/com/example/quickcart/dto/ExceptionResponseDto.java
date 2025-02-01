package com.example.quickcart.dto;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
public class ExceptionResponseDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
