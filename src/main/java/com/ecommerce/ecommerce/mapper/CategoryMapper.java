package com.ecommerce.ecommerce.mapper;
import com.ecommerce.ecommerce.domain.Category;
import com.ecommerce.ecommerce.domain.DTOS.CategoryResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toCategoryDTO(Category category);


    @Mapping(target = "products", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryResponseDTO categoryResponseDTO);

    List<CategoryResponseDTO> toCategoryDTOList(List<Category> categories);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateCategory(CategoryResponseDTO categoryResponseDTO, Category category);

}
