package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.domain.Client;
import com.ecommerce.ecommerce.domain.DTOS.ClientRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ClientResponseDTO;
import com.ecommerce.ecommerce.mapper.ClientMapper;
import com.ecommerce.ecommerce.repository.ClientRepository;
import com.ecommerce.ecommerce.utils.CPFValidator;
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
        validateClient(clientRequestDTO);

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
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível atualizar os dados porque o cliente não foi encontrado."));

        validateClient(clientRequestDTO, existingClient);

        clientMapper.updateClient(clientRequestDTO, existingClient);
        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.toClientResponseDTO(updatedClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível excluir o cliente porque ele não foi encontrado."));
        clientRepository.delete(client);
    }

    public List<Object> getClientOrders(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar os pedidos porque o cliente não foi encontrado."));
        return client.getOrders().stream().collect(Collectors.toList());
    }

    private void validateClient(ClientRequestDTO clientRequestDTO) {
        validateClient(clientRequestDTO, null);
    }

    private void validateClient(ClientRequestDTO clientRequestDTO, Client existingClient) {

        if (!CPFValidator.isValidCPF(clientRequestDTO.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        clientRepository.findByEmail(clientRequestDTO.getEmail()).ifPresent(client -> {
            if (existingClient == null || !client.getId().equals(existingClient.getId())) {
                throw new IllegalArgumentException("E-mail já está em uso.");
            }
        });

        if (!isValidEmail(clientRequestDTO.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && email.matches(emailRegex);
    }
}
