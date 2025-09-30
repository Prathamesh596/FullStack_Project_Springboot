package com.bullet.product.service;

import com.bullet.product.dto.CategoryDTO;
import com.bullet.product.entity.Category;
import com.bullet.product.mapper.CategoryMapper;
import com.bullet.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//logic
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }

    // READ: Get by ID
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(CategoryMapper::toCategoryDTO).orElse(null);
    }

    // READ: Get all
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(categoryDTO.getName());
                    existing.setDescription(categoryDTO.getDescription());
                    // update products if needed
                    Category updated = categoryRepository.save(existing);
                    return CategoryMapper.toCategoryDTO(updated);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
