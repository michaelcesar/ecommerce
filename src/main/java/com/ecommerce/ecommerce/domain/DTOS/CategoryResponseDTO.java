package com.ecommerce.ecommerce.domain.DTOS;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/*@Schema(description = "Category Response DTO")*/
public class CategoryResponseDTO {

    /*@Schema(description = "Category id", example = "1")*/
    private Long id;

    /*@Schema(description = "Category name", example = "Electronics")*/
    private String name;

    /*@Schema(description = "Category description", example = "Electronic devices")*/
    private String description;
}
