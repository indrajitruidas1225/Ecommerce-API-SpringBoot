package com.Indrajit.ecommerce_api.dto;

import com.Indrajit.ecommerce_api.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderResponse {
    private Long id;
    private String status;
    private Double totalAmount;
    private String userName;
    private List<OrderItemResponse> items;
}
