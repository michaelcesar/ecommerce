package com.ecommerce.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String cep;

    @ManyToOne      //ligação das operações entre as tabelas
    @JoinColumn(name = "client_id")
    private Client client;
}
