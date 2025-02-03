package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.domain.Category;
import com.ecommerce.ecommerce.domain.DTOS.CategoryRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.CategoryResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CategoryMapper {

    @Mapping(source = "products", target = "products")
    CategoryResponseDTO toCategoryDTO(Category category);

    List<CategoryResponseDTO> toCategoryDTOList(List<Category> categories);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryResponseDTO categoryResponseDTO);

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryRequestDTO categoryRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toUpdateCategory(CategoryResponseDTO categoryResponseDTO, @MappingTarget Category category);
}
