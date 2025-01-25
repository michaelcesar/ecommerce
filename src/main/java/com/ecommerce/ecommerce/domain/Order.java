package com.ecommerce.ecommerce.domain;

import com.ecommerce.ecommerce.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column
    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();
}

