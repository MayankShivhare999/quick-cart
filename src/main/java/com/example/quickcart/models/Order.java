package com.example.quickcart.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
@Table(name = "purchase_order")
public class Order extends BaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    private Double totalAmount;
    @CreatedDate
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
}
