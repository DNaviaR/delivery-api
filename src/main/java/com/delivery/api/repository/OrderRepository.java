package com.delivery.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.api.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // SELECT * FROM orders WHERE user_username = ?
    // Spring es tan listo que puede navegar dentro del objeto user
    List<Order> findByUserUsername(String username);
}
