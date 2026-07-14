package com.example.productservice.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank(message = "Không được để trống tên sản phẩm")
    private String name;

    @NotNull(message = "Không được để trống giá sản phẩm")
    @Min(value = 1, message = "Sản phẩm không được có giá nhỏ hơn 1")
    private BigDecimal price;

    @NotNull(message = "Không được để trống số lượng tồn")
    @Min(value = 0, message = "Sản phẩm không được có số tồn nhỏ hơn 0")
    private Integer stockQuantity;
}
