package com.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String cpf;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "client")
    private Address address;

    /*@OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();*/
    }
