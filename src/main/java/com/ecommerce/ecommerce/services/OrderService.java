package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.domain.Client;
import com.ecommerce.ecommerce.domain.DTOS.OrderResponseDTO;
import com.ecommerce.ecommerce.domain.Order;
import com.ecommerce.ecommerce.domain.DTOS.OrderRequestDTO;
import com.ecommerce.ecommerce.domain.OrderItem;
import com.ecommerce.ecommerce.domain.Product;
import com.ecommerce.ecommerce.domain.enums.OrderStatus;
import com.ecommerce.ecommerce.repository.ClientRepository;
import com.ecommerce.ecommerce.mapper.OrderMapper;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public Order createOrder(OrderRequestDTO requestDTO) {

        Order order = new Order();
        order.setOrderDate(requestDTO.getOrderDate());

        Client client = clientRepository.findById(requestDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        order.setClient(client);

        List<OrderItem> items = requestDTO.getItems().stream().map(itemDTO -> {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

            if (product.getPrice() == null) {
                throw new IllegalArgumentException("O produto deve ter um preço válido.");
            }

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setPrice(product.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            item.setSubtotal(product.getPrice().multiply(new BigDecimal(itemDTO.getQuantity())));
            return item;
        }).toList();

        order.setItems(items);

        BigDecimal totalValue = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalValue(totalValue);

        order.setOrderStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return orderMapper.toOrderResponseDTO(order);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        try {
            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setOrderStatus(orderStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido. Use um dos seguintes: " +
                    String.join(", ",
                            java.util.Arrays.stream(OrderStatus.values())
                                    .map(OrderStatus::name)
                                    .toList()));
        }

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByClientId(Long clientId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getClient().getId().equals(clientId))
                .toList();
    }
}
