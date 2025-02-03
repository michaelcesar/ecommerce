package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Update Stock DTO")
public class UpdateStockRequestDTO {

    @Schema(description = "Product ID", example = "1")
    private Integer quantity;
}
