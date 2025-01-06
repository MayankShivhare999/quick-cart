package com.example.quickcart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class OrderItem extends BaseModel {
    @OneToOne
    private Product product;
    private Integer quantity;
    private Double price;
}
