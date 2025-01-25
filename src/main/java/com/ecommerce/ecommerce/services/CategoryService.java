package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.domain.Category;
import com.ecommerce.ecommerce.domain.Product;
import com.ecommerce.ecommerce.domain.DTOS.CategoryRequestDTO;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
    }

    public Category updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        var category = getCategoryById(id);
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        var category = getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Transactional
    public Category addProductToCategory(Long categoryId, Long productId) {
        var category = getCategoryById(categoryId);
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (!category.getProducts().contains(product)) {
            category.getProducts().add(product);
        }

        if (!product.getCategories().contains(category)) {
            product.getCategories().add(category);
        }

        productRepository.save(product);
        return categoryRepository.save(category);
    }

    public void removeProductFromCategory(Long categoriaId, Long produtoId) {
        var category = getCategoryById(categoriaId);
        var product = productRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        category.getProducts().remove(product);
        categoryRepository.save(category);
    }
}
