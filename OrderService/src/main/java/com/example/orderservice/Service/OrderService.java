package com.example.orderservice.Service;

import com.example.orderservice.DTO.Request.OrderRequestDTO;
import com.example.orderservice.Entity.Order;

public interface OrderService {
    Order createOrder(OrderRequestDTO dto);

    Order getOrderById(Long id);
}
