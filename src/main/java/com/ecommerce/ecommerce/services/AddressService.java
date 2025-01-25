package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.domain.Address;
import com.ecommerce.ecommerce.domain.Client;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Address createAddress(Long clientId, Address address) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        address.setClient(client);
        return addressRepository.save(address);
    }

    public List<Address> getAddressesByClientId(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Cliente não .");
        }
        return addressRepository.findByClientId(clientId);
    }

    @Transactional
    public Address updateAddress(Long clientId, Long addressId, Address address) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));

        if (!existingAddress.getClient().getId().equals(client.getId())) {
            throw new IllegalArgumentException("Endereço não pertence ao cliente.");
        }

        existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        existingAddress.setCep(address.getCep());
        existingAddress.setState(address.getState());

        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(Long clientId, Long addressId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));

        if (!address.getClient().getId().equals(client.getId())) {
            throw new IllegalArgumentException("Endereço não pertence ao cliente.");
        }

        addressRepository.delete(address);
    }
}
