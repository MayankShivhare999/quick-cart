package com.example.quickcart.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends BaseModel {
    private String name;
    private String description;
}
