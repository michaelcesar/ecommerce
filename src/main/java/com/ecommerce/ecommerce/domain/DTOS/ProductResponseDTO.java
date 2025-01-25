package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Schema(description = "Product Response DTO")
public class ProductResponseDTO {

    @Schema(description = "Product ID", example = "1")
    private Long id;

    @Schema(description = "Product name", example = "Smartphone")
    private String name;

    @Schema(description = "Product description", example = "A smartphone with 128GB storage")
    private String description;

    @Schema(description = "Product price", example = "799.99")
    private BigDecimal price;

    @Schema(description = "Product category name", example = "Electronics")
    private String categoryName;
}
