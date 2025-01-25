package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.domain.Client;
import com.ecommerce.ecommerce.domain.DTOS.ClientRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ClientResponseDTO;
import com.ecommerce.ecommerce.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.ecommerce.mapper.ClientMapper;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @PostMapping
    @Operation(summary = "Cadastrar novo cliente")
    public ResponseEntity<ClientResponseDTO> createClient(@Validated @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO createdClient = clientService.createClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes (com paginação)")
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable pageable) {
        return ResponseEntity.ok(clientService.getAllClients(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        ClientResponseDTO responseDTO = clientMapper.toClientResponseDTO(client);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do cliente")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @Validated @RequestBody ClientRequestDTO clientRequestDTO) {
        return ResponseEntity.ok(clientService.updateClient(id, clientRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover cliente")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pedidos")
    @Operation(summary = "Listar pedidos do cliente")
    public ResponseEntity<List<Object>> getClientOrders(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientOrders(id));
    }
}
