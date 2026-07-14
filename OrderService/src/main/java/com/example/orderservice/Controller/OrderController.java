package com.example.orderservice.Controller;

import com.example.orderservice.DTO.Request.OrderRequestDTO;
import com.example.orderservice.DTO.Response.DataResponse;
import com.example.orderservice.DTO.Response.ProductResponse;
import com.example.orderservice.Entity.Order;
import com.example.orderservice.Service.OrderService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<DataResponse<Order>> createOrder (
            @Valid @RequestBody OrderRequestDTO dto)
    {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Đã thêm sản phẩm",
                        orderService.createOrder(dto),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Order>> getOrderById (
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Tìm thấy sản phẩm",
                        orderService.getOrderById(id),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.OK
        );
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<DataResponse<ProductResponse>> getProductFromProductService(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Tìm thấy sản phẩm",
                        orderService.getProductFromProductService(id),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.OK
        );
    }
}
