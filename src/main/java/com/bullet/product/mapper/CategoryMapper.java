package com.bullet.product.mapper;

import com.bullet.product.dto.CategoryDTO;
import com.bullet.product.entity.Category;

import java.util.Collections;
import java.util.stream.Collectors;

public class CategoryMapper {

    // ENTITY -> DTO
    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) return null;

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());

        if (category.getProducts() != null) {
            categoryDTO.setProducts(
                    category.getProducts().stream()
                            .map(ProductMapper::toProductDTO)
                            .collect(Collectors.toList())
            );
        } else {
            categoryDTO.setProducts(Collections.emptyList());
        }

        return categoryDTO;
    }

    // DTO -> ENTITY
    public static Category toCategoryEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) return null;

        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        if (categoryDTO.getProducts() != null) {
            category.setProducts(
                    categoryDTO.getProducts().stream()
                            .map(dto -> ProductMapper.toProductEntity(dto, category)) // pass category here
                            .collect(Collectors.toList())
            );
        } else {
            category.setProducts(Collections.emptyList());
        }

        return category;
    }
}
