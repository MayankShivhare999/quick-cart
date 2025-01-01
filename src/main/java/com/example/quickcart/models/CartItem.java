package com.example.quickcart.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartItem extends BaseModel{
    private Product product;
    private Integer quantity;
    private Double price;
}
