package com.ecommerce.ecommerce.domain.DTOS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Address Request DTO")
public class AddressRequestDTO {

    @Schema(description = "Street name", example = "Rua do Chafariz")
    private String street;

    @Schema(description = "House number", example = "123")
    private String number;

    @Schema(description = "District name", example = "Centro")
    private String district;

    @Schema(description = "City name", example = "Serrinha dos Pintos")
    private String city;

    @Schema(description = "State name", example = "RN")
    private String state;

    @Schema(description = "CEP", example = "59808-000")
    private String cep;
}
