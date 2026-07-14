package com.example.orderservice.Service.impl;

import com.example.orderservice.DTO.Request.OrderRequestDTO;
import com.example.orderservice.DTO.Response.DataResponse;
import com.example.orderservice.DTO.Response.ProductResponse;
import com.example.orderservice.Entity.Order;
import com.example.orderservice.Exception.ResourceNotFoundException;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.Service.OrderService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final RestTemplate restTemplate;

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

    @Override
    public ProductResponse getProductFromProductService(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("ProductService");

        if (instances.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Product Service hiện không khả dụng (Không tìm thấy service)"
            );
        }

        ServiceInstance productInstance = instances.get(0);

        String baseUrl = productInstance.getUri().toString();

        String targetUrl = baseUrl + "/api/v1/products/" + productId;

        DataResponse<ProductResponse> response = restClient.get()
                .uri(targetUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return response.getData();

    }

    @Override
    public ProductResponse getProductFromProductService2(Long productId) {

        String targetUrl = "http://ProductService/api/v1/products/" + productId;

        ResponseEntity<DataResponse<ProductResponse>> response =
                restTemplate.exchange(
                        targetUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<DataResponse<ProductResponse>>() {}
                );

        return response.getBody().getData();

    }
}
