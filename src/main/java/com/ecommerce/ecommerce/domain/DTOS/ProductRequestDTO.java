package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Schema(description = "Product Request DTO")
public class ProductRequestDTO {

    @Schema(description = "Product name", example = "Smartphone")
    @NotBlank(message = "Nome do produto é obrigatório")
    private String name;

    @Schema(description = "Product description", example = "A smartphone with 128GB storage")
    private String description;

    @Min(value = 1, message = "A quantidade deve ser maior que zero.")
    @NotNull(message = "Quantidade do produto é obrigatória")
    private Integer quantity;

    @Schema(description = "Product price", example = "799.99")
    @NotNull(message = "Preço do produto é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private BigDecimal price;

    @Schema(description = "Product category ID", example = "1")
    private Long categoryId;
}
