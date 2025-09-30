package com.example.ordersystem.dto.response;

import com.example.ordersystem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}
