package com.example.customerservice.Controller;

import com.example.customerservice.DTO.request.CustomerRequestDTO;
import com.example.customerservice.DTO.request.LoginRequest;
import com.example.customerservice.DTO.response.CustomerResponseDTO;
import com.example.customerservice.DTO.response.DataResponse;
import com.example.customerservice.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<DataResponse<CustomerResponseDTO>> register (
            @RequestBody CustomerRequestDTO dto
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Đã đăng ký",
                        customerService.register(dto),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CustomerResponseDTO>> getCustomerById (
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Chi tiết khách hàng: ",
                        customerService.getCustomerByID(id),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.OK
        );
    }

    @PutMapping("/login")
    public ResponseEntity<DataResponse<CustomerResponseDTO>> login (
            @RequestBody LoginRequest request
    ) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        true,
                        "Chào mừng bạn đã tới!",
                        customerService.login(request),
                        null,
                        LocalDateTime.now()
                ), HttpStatus.OK
        );
    }

}
