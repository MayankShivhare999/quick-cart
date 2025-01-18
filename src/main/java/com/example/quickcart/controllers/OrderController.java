package com.example.quickcart.controllers;

import com.example.quickcart.models.Order;
import com.example.quickcart.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@PathVariable UUID userId) {
        ResponseEntity<List<Order>> responseEntity = null;
        List<Order> allOrdersByUserId = orderService.getAllOrdersByUserId(userId);
        responseEntity = ResponseEntity.ok(allOrdersByUserId);
        return responseEntity;
    }
}
