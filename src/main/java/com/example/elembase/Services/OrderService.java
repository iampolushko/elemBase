package com.example.elembase.Services;

import com.example.elembase.Entitity.Order;
import com.example.elembase.repository.OrderRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public void createNewOrder(Order order) {
        orderRepo.save(order);
    }

}
