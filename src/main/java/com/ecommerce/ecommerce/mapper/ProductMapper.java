package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.domain.Product;
import com.ecommerce.ecommerce.domain.DTOS.ProductRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ProductResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryName", expression = "java(product.getCategories() != null && !product.getCategories().isEmpty() ? product.getCategories().get(0).getName() : null)")
    ProductResponseDTO toProductResponseDTO(Product product);

    List<ProductResponseDTO> toProductResponseDTOList(List<Product> products);

    Product toProduct(ProductRequestDTO productRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(ProductRequestDTO productRequestDTO, @MappingTarget Product product);
}
