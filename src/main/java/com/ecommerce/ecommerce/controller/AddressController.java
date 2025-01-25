package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.domain.DTOS.AddressRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.AddressResponseDTO;
import com.ecommerce.ecommerce.mapper.AddressMapper;
import com.ecommerce.ecommerce.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente/{clientId}/endereco")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@PathVariable Long clientId, @RequestBody AddressRequestDTO addressRequestDTO) {
        var address = addressMapper.toAddress(addressRequestDTO);
        var savedAddress = addressService.createAddress(clientId, address);
        return ResponseEntity.ok(addressMapper.toAddressResponseDTO(savedAddress));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAddresses(@PathVariable Long clientId) {
        var addresses = addressService.getAddressesByClientId(clientId);
        var responseDTOs = addresses.stream().map(addressMapper::toAddressResponseDTO).toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long clientId, @PathVariable Long addressId, @RequestBody AddressRequestDTO addressRequestDTO) {
        var address = addressMapper.toAddress(addressRequestDTO);
        var updatedAddress = addressService.updateAddress(clientId, addressId, address);
        return ResponseEntity.ok(addressMapper.toAddressResponseDTO(updatedAddress));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long clientId, @PathVariable Long addressId) {
        addressService.deleteAddress(clientId, addressId);
        return ResponseEntity.noContent().build();
    }
}
