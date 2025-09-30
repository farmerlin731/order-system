package com.example.ordersystem.controller;

import com.example.ordersystem.dto.response.OrderDTO;
import com.example.ordersystem.model.Order;
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

//    @GetMapping
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }

    //DTO version
    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDTO::fromEntity)
                .toList();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
