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

    @OneToOne(cascade = CascadeType.ALL) //ligação das operações entre as tabelas
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
