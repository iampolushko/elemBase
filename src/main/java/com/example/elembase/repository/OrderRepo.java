package com.example.elembase.repository;

import com.example.elembase.Entitity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query(value = "select id_product from diplom.order where id_user = ?1", nativeQuery = true)
    List<Long> getProductsIdsByUsersIds(Long userId);

    @Query(value = "select * from diplom.order", nativeQuery = true)
    List<Order> getAllOrders();

    @Query(value = "select * from diplom.order", nativeQuery = true)
    void saveOrder();

}
