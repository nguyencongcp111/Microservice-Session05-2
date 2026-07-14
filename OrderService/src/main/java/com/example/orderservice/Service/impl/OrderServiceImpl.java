package com.example.orderservice.Service.impl;

import com.example.orderservice.DTO.Request.OrderRequestDTO;
import com.example.orderservice.Entity.Order;
import com.example.orderservice.Exception.ResourceNotFoundException;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderRequestDTO dto) {
        Order order = Order.builder()
                .customerId(dto.getCustomerId())
                .productId(dto.getProductId())
                .orderDate(LocalDateTime.now())
                .quantity(dto.getQuantity())
                .totalAmount(dto.getTotalAmount())
                .build();

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy sản phẩm ")
        );
    }
}
