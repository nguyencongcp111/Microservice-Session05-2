package com.example.customerservice.Service.impl;

import com.example.customerservice.DTO.request.CustomerRequestDTO;
import com.example.customerservice.DTO.request.LoginRequest;
import com.example.customerservice.DTO.response.CustomerResponseDTO;
import com.example.customerservice.Entity.Customer;
import com.example.customerservice.Exception.ResourceNotFoundException;
import com.example.customerservice.Mapper.CustomerMapper;
import com.example.customerservice.Repository.CustomerRepository;
import com.example.customerservice.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDTO register(CustomerRequestDTO dto) {
        Customer customer = customerMapper.toEntity(dto);

        return customerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDTO getCustomerByID(Long id) {
        return customerRepository.findById(id)
                .stream()
                .map(customerMapper::toDTO)
                .findFirst().orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Không tìm thấy khách hàng với id " + id
                        )
                );
    }

    @Override
    public CustomerResponseDTO login(LoginRequest request) {
        Customer customer = customerRepository.findCustomerByEmail(
                request.getEmail()
        ).orElseThrow(() -> new ResourceNotFoundException(
                "Sai tên tài khoản hoặc mật khẩu"
        ));

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                customer.getPassword()
        );

        if (!match) {
            throw new ResourceNotFoundException("Sai tên tài khoản hoặc mật khẩu");
        }

        return customerMapper.toDTO(customer);
    }
}