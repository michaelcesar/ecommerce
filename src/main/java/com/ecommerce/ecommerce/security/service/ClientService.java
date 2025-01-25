package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.domain.Client;
import com.ecommerce.ecommerce.domain.DTOS.ClientRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ClientResponseDTO;
import com.ecommerce.ecommerce.domain.Order;
import com.ecommerce.ecommerce.mapper.ClientMapper;
import com.ecommerce.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        Client client = clientMapper.toClient(clientRequestDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toClientResponseDTO(savedClient);
    }

    public Page<ClientResponseDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toClientResponseDTO);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível atualizar os dados porque o cliente não encontrado."));
        clientMapper.updateClient(clientRequestDTO, client);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toClientResponseDTO(updatedClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível excluir o cliente porque ele não foi encontrado."));
        clientRepository.delete(client);
    }

    public List<Object> getClientOrders(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar os pedidos porque o cliente não foi encontrado."));
        return client.getOrders().stream().collect(Collectors.toList());
    }
}
