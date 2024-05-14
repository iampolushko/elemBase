package com.example.elembase.repository;

import com.example.elembase.Entitity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query(value = "select id_product from diplom.order where id_user = ?1", nativeQuery = true)
    List<Long> getProductsIdsByUsersIds(Long userId);

    @Query(value = "select * from diplom.order", nativeQuery = true)
    List<Order> getAllOrders();

    @Modifying
    @Query(value = "INSERT INTO `diplom`.`order` (`id_product`, `id_user`) VALUES (:id_product,:id_user)", nativeQuery = true)
    @Transactional
    void saveOrder(@Param("id_product") Long id_product, @Param("id_user") Long id_user);

}
