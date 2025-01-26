package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.domain.DTOS.OrderRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.OrderResponseDTO;
import com.ecommerce.ecommerce.domain.Order;
import com.ecommerce.ecommerce.domain.enums.OrderStatus;
import com.ecommerce.ecommerce.mapper.OrderMapper;
import com.ecommerce.ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO responseDTO = orderService.getOrderById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(updatedOrder));
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByClientId(@PathVariable Long clientId) {
        List<Order> orders = orderService.getOrdersByClientId(clientId);
        List<OrderResponseDTO> responseDTOs = orderMapper.toOrderResponseDTOList(orders);
        return ResponseEntity.ok(responseDTOs);
    }
}
