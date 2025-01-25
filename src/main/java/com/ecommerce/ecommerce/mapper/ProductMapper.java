package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.domain.Product;
import com.ecommerce.ecommerce.domain.DTOS.ProductRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ProductResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductResponseDTO toProductResponseDTO(Product product);

    Product toProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> toProductResponseDTOList(List<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(ProductRequestDTO productRequestDTO, @MappingTarget Product product);
}
