package com.example.elembase.Services;

import com.example.elembase.Entitity.Order;
import com.example.elembase.repository.OrderRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public void createNewOrder(Order order) {
        orderRepo.save(order);
    }

    public List<Long> getProductsId(Long userId) {
        return orderRepo.getProductsIdsByUsersIds(userId);
    }

    public List<Order> getAllOrders() {
//        return orderRepo.findAll();
        return orderRepo.getAllOrders();
    }

}
