package com.bullet.product.controller;

import com.bullet.product.dto.CategoryDTO;
import com.bullet.product.exception.CategoryAlreadyExistException;
import com.bullet.product.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Category Rest Api crud Operations",
        description = "Create,Update,Read, Delete Operations for Category Rest Api"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // CREATE Category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {

            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
//        } catch (CategoryAlreadyExistException ex) {
//            // Return 409 Conflict if category already exists
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
   //     }
    }

    // GET All Categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // GET Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    // UPDATE Category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    // DELETE Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
