package com.example.productservice.Controller;

import com.example.productservice.DTO.Request.ProductRequestDTO;
import com.example.productservice.DTO.Response.DataResponse;
import com.example.productservice.Entity.Product;
import com.example.productservice.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<DataResponse<Product>> addProduct(
            @Valid @RequestBody ProductRequestDTO dto
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Đã tạo sản phẩm",
                        productService.addProduct(dto),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Đã tạo sản phẩm",
                        productService.getProductById(id),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProduct() {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Đã tạo sản phẩm",
                        productService.getAllProduct(),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.OK
        );
    }
}
