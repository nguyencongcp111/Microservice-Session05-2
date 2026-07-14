package com.example.productservice.Service.impl;

import com.example.productservice.DTO.Request.ProductRequestDTO;
import com.example.productservice.Entity.Product;
import com.example.productservice.Exception.ResourceNotFoundException;
import com.example.productservice.Repository.ProductRepository;
import com.example.productservice.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(ProductRequestDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stockQuantity(dto.getStockQuantity())
                .build();

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy id "  + id
                ));
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
