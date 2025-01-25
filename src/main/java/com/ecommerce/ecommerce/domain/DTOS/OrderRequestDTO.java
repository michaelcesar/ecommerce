package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "Order Request DTO")
public class OrderRequestDTO {

    @Schema(description = "Order date", example = "2025-01-01T10:00:00")
    @NotNull(message = "Data do pedido é obrigatória")
    private LocalDateTime orderDate;

    @Schema(description = "Total value of the order", example = "250.75")
    private BigDecimal totalValue;

    @Schema(description = "Client ID associated with the order", example = "1")
    @NotNull(message = "Cliente é obrigatório")
    private Long clientId;

    @Schema(description = "List of items in the order")
    @NotNull(message = "A lista de itens do pedido não pode ser nula")
    private List<OrderItemRequestDTO> items;
}
