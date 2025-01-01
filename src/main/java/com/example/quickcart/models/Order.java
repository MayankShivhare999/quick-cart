package com.example.quickcart.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends BaseModel {
    private User user;
    private List<OrderItem> orderItems;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
}
