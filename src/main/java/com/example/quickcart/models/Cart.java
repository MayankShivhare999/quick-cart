package com.example.quickcart.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cart extends BaseModel {
    private User user;
    private List<CartItem> products;
    private Double totalAmount;

}
