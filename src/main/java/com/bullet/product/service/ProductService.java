package com.bullet.product.service;

import com.bullet.product.dto.ProductDTO;
import com.bullet.product.entity.Category;
import com.bullet.product.entity.Product;
import com.bullet.product.mapper.ProductMapper;
import com.bullet.product.repository.CategoryRepository;
import com.bullet.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private  ProductRepository productRepository;
    private  CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // CREATE Product
    public ProductDTO createProduct(ProductDTO productDTO, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Product product = ProductMapper.toProductEntity(productDTO, category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toProductDTO(savedProduct);
    }

    // READ: Get all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    // READ: Get product by ID
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toProductDTO(product);
    }

    // UPDATE product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, Long categoryId) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        existing.setName(productDTO.getName());
        existing.setDescription(productDTO.getDescription());
        existing.setPrice(productDTO.getPrice());
        existing.setCategory(category);

        Product updatedProduct = productRepository.save(existing);
        return ProductMapper.toProductDTO(updatedProduct);
    }

    // DELETE product
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
