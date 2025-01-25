package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.domain.DTOS.CategoryRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.CategoryResponseDTO;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        var category = categoryMapper.toCategory(categoryRequestDTO);
        var savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(categoryMapper.toCategoryDTO(savedCategory));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        var categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryMapper.toCategoryDTOList(categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        var category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryMapper.toCategoryDTO(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        var updatedCategory = categoryService.updateCategory(id, categoryRequestDTO);
        return ResponseEntity.ok(categoryMapper.toCategoryDTO(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<Void> addProductToCategory(@PathVariable Long categoryId, @PathVariable Long produtoId) {
        categoryService.addProductToCategory(categoryId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<Void> removeProductFromCategory(@PathVariable Long categoryId, @PathVariable Long produtoId) {
        categoryService.removeProductFromCategory(categoryId, produtoId);
        return ResponseEntity.noContent().build();
    }
}
