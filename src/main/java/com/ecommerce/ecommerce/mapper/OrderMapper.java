package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.domain.DTOS.OrderItemResponseDTO;
import com.ecommerce.ecommerce.domain.Order;
import com.ecommerce.ecommerce.domain.DTOS.OrderRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.OrderResponseDTO;
import com.ecommerce.ecommerce.domain.OrderItem;
import com.ecommerce.ecommerce.domain.enums.OrderStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "orderStatus", expression = "java(order.getOrderStatus() != null ? order.getOrderStatus().name() : null)")
    @Mapping(target = "orderStatusDescription", expression = "java(order.getOrderStatus() != null ? order.getOrderStatus().getDescription() : null)")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "items", source = "items")
    OrderResponseDTO toOrderResponseDTO(Order order);

    List<OrderResponseDTO> toOrderResponseDTOList(List<Order> orders);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem item);

    List<OrderItemResponseDTO> toOrderItemResponseDTOList(List<OrderItem> items);
}

