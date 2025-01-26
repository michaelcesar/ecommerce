package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.domain.Product;
import com.ecommerce.ecommerce.domain.Category;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(Product product, Long categoryId) {

        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        if (product.getQuantity() == null || product.getQuantity() <= 0) {
            throw new IllegalArgumentException("A quantidade do produto deve ser maior que zero.");
        }

        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElse(null);

            if (category != null) {
                product.getCategories().add(category);
            }
        }

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public Product updateStock(Long id, Integer quantity) {
        Product product = getProductById(id);
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        return productRepository.findAll().stream()
                .filter(product -> product.getCategories().contains(category))
                .toList();
    }
}