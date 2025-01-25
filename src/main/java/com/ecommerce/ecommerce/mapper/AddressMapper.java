package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.domain.Address;
import com.ecommerce.ecommerce.domain.DTOS.AddressRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.AddressResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    AddressResponseDTO toAddressResponseDTO(Address address);

    //AddressRequestDTO para Address
    Address toAddress(AddressRequestDTO addressRequestDTO);

    //conversão de uma lista de endereços para uma lista de AddressResponseDTO
    List<AddressResponseDTO> toAddressResponseDTOList(List<Address> addresses);

    //atualizando os dados de Address com base em um AddressRequestDTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDTO(AddressRequestDTO addressRequestDTO, @MappingTarget Address address);
}
