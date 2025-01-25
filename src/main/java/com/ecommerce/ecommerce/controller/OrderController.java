package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.domain.DTOS.OrderRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.OrderResponseDTO;
import com.ecommerce.ecommerce.domain.enums.OrderStatus;
import com.ecommerce.ecommerce.mapper.OrderMapper;
import com.ecommerce.ecommerce.services.OrderService;
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
        var order = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        var orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        var order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(order));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        var updatedOrder = orderService.updateOrderStatus(id, String.valueOf(status));
        var responseDTO = orderMapper.toOrderResponseDTO(updatedOrder);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByClient(@PathVariable Long clientId) {
        var orders = orderService.getOrdersByClientId(clientId);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(orders));
    }
}
