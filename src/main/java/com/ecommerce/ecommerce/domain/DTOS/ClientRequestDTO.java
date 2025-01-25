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

    @Schema(description = "Client email", example = "michael@example.com")
    @Email(message = "Email deve ser válido")
    private String email;

    @Schema(description = "Client password", example = "123")
    private String password;
}
