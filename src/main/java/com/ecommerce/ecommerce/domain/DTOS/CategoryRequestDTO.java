package com.ecommerce.ecommerce.domain.DTOS;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/*@Schema(description = "Category Request DTO")*/
public class CategoryRequestDTO {

        /*@Schema(description = "Category name", example = "Electronics")*/
        /*@NotBlank(message = "Nome é obrigatório")*/
        private String name;

        /*@Schema(description = "Category description", example = "Electronic devices")*/
        private String description;
}
