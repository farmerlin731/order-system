package com.example.ordersystem.dto.response;

import com.example.ordersystem.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String customerName;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;

    public static OrderDTO fromEntity(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(OrderItemDTO::fromEntity)
                .toList();
        return new OrderDTO(order.getId(), order.getCustomerName(), order.getCreatedAt(), itemDTOs);
    }
}
