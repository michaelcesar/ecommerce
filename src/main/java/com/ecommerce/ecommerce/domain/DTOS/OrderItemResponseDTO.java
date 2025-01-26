package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Schema(description = "Order Item Response DTO")
public class OrderItemResponseDTO {

    @Schema(description = "Product ID", example = "1")
    private Long productId;

    @Schema(description = "Product name", example = "Smartphone")
    private String productName;

    @Schema(description = "Quantity", example = "2")
    private Integer quantity;

    @Schema(description = "Price", example = "799.99")
    private BigDecimal price;

    @Schema(description = "Subtotal", example = "1599.98")
    private BigDecimal subtotal;
}
