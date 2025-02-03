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

    @Schema(description = "Client ID associated with the order", example = "1")
    @NotNull(message = "Cliente é obrigatório")
    private Long clientId;

    @Schema(description = "List of items in the order")
    @NotNull(message = "A lista de itens do pedido não pode ser nula")
    private List<OrderItemRequestDTO> items;
}
