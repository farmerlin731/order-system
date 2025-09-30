package com.example.ordersystem.controller;

import com.example.ordersystem.dto.response.ProductDTO;
import com.example.ordersystem.model.Product;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    //Original version
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    //DTO version
    @GetMapping
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::fromEntity)
                .toList();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
