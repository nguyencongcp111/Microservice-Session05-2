package com.example.orderservice.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDTO {
    @NotNull(message = "Không được để trống id khách hàng")
    private Long customerId;

    @NotNull(message = "Không được để trống id sản phẩm")
    private Long productId;

    @NotNull(message = "Không được để trống số lượng sản phẩm")
    @Min(value = 1, message = "Số lượng sản phẩm không được bằng hoặc nhỏ hơn 0")
    private Integer quantity;

    @NotNull(message = "Không được để trống tổng giá tiền sản phẩm")
    private BigDecimal totalAmount;
}
