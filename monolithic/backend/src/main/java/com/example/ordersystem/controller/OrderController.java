package com.example.ordersystem.controller;

import com.example.ordersystem.model.CustomerOrder;
import com.example.ordersystem.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }
}
