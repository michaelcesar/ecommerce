package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "Category Response DTO")
public class CategoryResponseDTO {

    @Schema(description = "Category id", example = "1")
    private Long id;

    @Schema(description = "Category name", example = "Electronics")
    private String name;

    @Schema(description = "Category description", example = "Electronic devices")
    private String description;

    @Schema(description = "Category products")
    private List<ProductResponseDTO> products;

}
