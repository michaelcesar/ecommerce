package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Client Request DTO")
public class ClientRequestDTO {

    @Schema(description = "Client name", example = "John Doe")
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Schema(description = "Client email", example = "john.doe@example.com")
    @Email(message = "Email deve ser válido")
    private String email;

    @Schema(description = "Client phone number", example = "+1-202-555-0173")
    private String phone;
}
