package com.example.customerservice.Service;

import com.example.customerservice.DTO.request.CustomerRequestDTO;
import com.example.customerservice.DTO.request.LoginRequest;
import com.example.customerservice.DTO.response.CustomerResponseDTO;
import com.example.customerservice.Entity.Customer;

import java.util.Optional;

public interface CustomerService {
    CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO getCustomerByID(Long id);

    CustomerResponseDTO login(LoginRequest request);
}
