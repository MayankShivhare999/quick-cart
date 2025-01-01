package com.example.quickcart.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends BaseModel {
    private String name;
    private String description;
    private Double price;
    private Integer quantityInStock;
    private Category category;
    private String imageUrl;
}
