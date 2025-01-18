package com.example.quickcart.services.impl;

import com.example.quickcart.exceptions.UserNotFoundException;
import com.example.quickcart.models.Order;
import com.example.quickcart.models.OrderItem;
import com.example.quickcart.models.OrderStatus;
import com.example.quickcart.models.User;
import com.example.quickcart.repositories.OrderRepository;
import com.example.quickcart.services.OrderService;
import com.example.quickcart.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;


    public OrderServiceImpl(UserService userService, OrderRepository orderRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrdersByUserId(UUID userId) throws UserNotFoundException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return orderRepository.findByUser_Id(userId);
        }
        return List.of();
    }

    @Override
    public Order createOrder(UUID userId, List<OrderItem> orderItems) throws UserNotFoundException {
        User user = userService.getUserById(userId);

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalAmount(getTotalPrice(orderItems));
        order.setOrderStatus(OrderStatus.PENDING);
        return orderRepository.save(order);

    }

    private Double getTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity()).sum();
    }
}
