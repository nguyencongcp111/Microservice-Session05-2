package com.example.productservice.Service;

import com.example.productservice.DTO.Request.ProductRequestDTO;
import com.example.productservice.Entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductRequestDTO dto);

    Product getProductById(Long id);

    List<Product> getAllProduct();
}
