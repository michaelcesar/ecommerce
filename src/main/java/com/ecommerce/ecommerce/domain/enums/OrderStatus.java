package com.ecommerce.ecommerce.domain.enums;

public enum OrderStatus {
    PENDING ("Aguardando pagamento"),
    PAID ("Pago"),
    SHIPPED("Enviado"),
    DELIVERED("Entregue"),
    CANCELED("Cancelado"),
    CANCEL("Cancelar");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
