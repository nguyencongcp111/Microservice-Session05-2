package com.example.customerservice.Mapper;

import com.example.customerservice.DTO.request.CustomerRequestDTO;
import com.example.customerservice.DTO.response.CustomerResponseDTO;
import com.example.customerservice.Entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final PasswordEncoder passwordEncoder;

    public CustomerResponseDTO toDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());

        return dto;
    }

    public Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();

        customer.setEmail(dto.getEmail());
        customer.setFullName(dto.getFullName());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        return customer;
    }
}
