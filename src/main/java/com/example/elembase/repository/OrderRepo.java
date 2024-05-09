package com.example.elembase.repository;

import com.example.elembase.Entitity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
