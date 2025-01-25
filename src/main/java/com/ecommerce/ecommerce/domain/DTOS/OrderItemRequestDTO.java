package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Schema(description = "Order Item Request DTO")
public class OrderItemRequestDTO {

    @Schema(description = "Product ID associated with the item", example = "1")
    @NotNull(message = "O ID do produto é obrigatório")
    private Long productId;

    @Schema(description = "Quantity of the product", example = "2")
    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantity;

    @Schema(description = "Price of the product", example = "125.50")
    @NotNull(message = "O preço do produto é obrigatório")
    private BigDecimal price;
}
