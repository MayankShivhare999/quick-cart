package com.example.quickcart.services;

import com.example.quickcart.exceptions.UserNotFoundException;
import com.example.quickcart.models.Order;
import com.example.quickcart.models.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<Order> getAllOrdersByUserId(UUID userId);

    Order createOrder(UUID userId, List<OrderItem> orderItems);

    List<Order> getAllOrders();
}
