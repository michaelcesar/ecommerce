package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.domain.DTOS.ProductRequestDTO;
import com.ecommerce.ecommerce.domain.DTOS.ProductResponseDTO;
import com.ecommerce.ecommerce.mapper.ProductMapper;
import com.ecommerce.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        var product = productMapper.toProduct(productRequestDTO);
        var savedProduct = productService.createProduct(product, productRequestDTO.getCategoryId());
        return ResponseEntity.ok(productMapper.toProductResponseDTO(savedProduct));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponseDTO> products = productService.getAllProducts(pageable, name, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        var product = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.toProductResponseDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        var product = productMapper.toProduct(productRequestDTO);
        var updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(productMapper.toProductResponseDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<ProductResponseDTO> updateStock(@PathVariable Long id, @RequestParam Integer quantity) {
        var updatedProduct = productService.updateStock(id, quantity);
        return ResponseEntity.ok(productMapper.toProductResponseDTO(updatedProduct));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable Long categoriaId) {
        var products = productService.getProductsByCategory(categoriaId);
        return ResponseEntity.ok(productMapper.toProductResponseDTOList(products));
    }
}
