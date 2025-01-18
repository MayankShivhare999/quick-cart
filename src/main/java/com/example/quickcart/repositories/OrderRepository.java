package com.example.quickcart.repositories;

import com.example.quickcart.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUser_Id(UUID userId);
}
