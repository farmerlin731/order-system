package com.example.ordersystem;

import com.example.ordersystem.model.CustomerOrder;
import com.example.ordersystem.model.OrderItem;
import com.example.ordersystem.model.Product;
import com.example.ordersystem.repository.OrderItemRepository;
import com.example.ordersystem.repository.OrderRepository;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;

@SpringBootApplication
public class OrderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSystemApplication.class, args);
    }

    @Bean
    @Order(1)
        // 第一個執行
    CommandLineRunner initProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                        new Product(null, "iPhone 16", 39999.0),
                        new Product(null, "MacBook Pro 14", 69999.0),
                        new Product(null, "AirPods Pro 3", 8999.0)
                ));
                System.out.println("Products initialized.");
            }
        };
    }

    // 初始化訂單
    @Bean
    @Order(2)
    // 第二個執行
    CommandLineRunner initOrders(OrderRepository orderRepository) {
        return args -> {
            if (orderRepository.count() == 0) {
                CustomerOrder customerOrder = CustomerOrder.builder()
                        .customerName("Alice")
                        .build();
                orderRepository.save(customerOrder);
                System.out.println("Orders initialized.");
            }
        };
    }

    // 初始化訂單項目
    @Bean
    @Order(3)
    // 第三個執行
    CommandLineRunner initOrderItems(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {
        return args -> {
            if (orderItemRepository.count() == 0) {
                CustomerOrder customerOrder = orderRepository.findAll().get(0); // 假設只有一筆訂單
                Product iphone = productRepository.findByName("iPhone 16")
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                orderItemRepository.save(new OrderItem(null, customerOrder, iphone, 1));
                System.out.println("OrderItems initialized.");
            }
        };
    }
}
