package com.example.quickcart.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseModel {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
