package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Client Response DTO")
public class ClientResponseDTO {

    @Schema(description = "Client ID", example = "1")
    private Long id;

    @Schema(description = "Client name", example = "John Doe")
    private String name;

    @Schema(description = "Client email", example = "john.doe@example.com")
    private String email;

   /* @Schema(description = "Client phone number", example = "+1-202-555-0173")
    private String phone;*/

    @Schema(description = "Client cpf", example = "000.000.000-00")
    private String cpf;
}
