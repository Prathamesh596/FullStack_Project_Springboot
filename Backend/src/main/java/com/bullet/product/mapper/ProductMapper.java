package com.bullet.product.mapper;

import com.bullet.product.dto.ProductDTO;
import com.bullet.product.entity.Category;
import com.bullet.product.entity.Product;
import io.swagger.v3.oas.annotations.tags.Tag;


public class ProductMapper {

    // ENTITY -> DTO
    public static ProductDTO toProductDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getId() : null // categoryId
        );
    }

    // DTO -> ENTITY
    public static Product toProductEntity(ProductDTO productDTO, Category category) {
        if (productDTO == null) return null;

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
