package com.example.quickcart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Cart extends BaseModel {
    @OneToOne
    private User user;
    @OneToMany
    private List<CartItem> products;
    private Double totalAmount;

}
