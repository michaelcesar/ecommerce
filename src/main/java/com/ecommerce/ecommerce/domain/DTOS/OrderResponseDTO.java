package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "Order Response DTO")
public class OrderResponseDTO {

    @Schema(description = "Order ID", example = "1")
    private Long id;

    @Schema(description = "Order date", example = "2025-01-01T10:00:00")
    private LocalDateTime orderDate;

    @Schema(description = "Total value of the order", example = "250.75")
    private BigDecimal totalValue;

    @Schema(description = "Client ID associated with the order", example = "1")
    private Long clientId;
}
