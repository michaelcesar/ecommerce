package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.domain.Client;
import com.ecommerce.ecommerce.domain.DTOS.ClientRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ClientResponseDTO;
import org.mapstruct.*;
                                    // evitando dados nulos
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    ClientResponseDTO toClientResponseDTO(Client client);

    Client toClient(ClientRequestDTO clientRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClient(ClientRequestDTO clientRequestDTO, @MappingTarget Client client);
}
