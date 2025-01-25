package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Category Request DTO")
public class CategoryRequestDTO {

        @Schema(description = "Category name", example = "Electronics")
        @NotBlank(message = "Nome é obrigatório")
        private String name;

        @Schema(description = "Category description", example = "Electronic devices")
        private String description;
}
