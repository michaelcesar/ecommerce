package com.ecommerce.ecommerce.domain.DTOS;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;

}
