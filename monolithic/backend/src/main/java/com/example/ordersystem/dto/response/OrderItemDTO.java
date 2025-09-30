package com.example.ordersystem.dto.response;


import com.example.ordersystem.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private Integer quantity;

    public static OrderItemDTO fromEntity(OrderItem item) {
        return new OrderItemDTO(item.getId(), ProductDTO.fromEntity(item.getProduct()), item.getQuantity());
    }
}