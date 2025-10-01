package com.bullet.product.controller;

import com.bullet.product.dto.ProductDTO;
import com.bullet.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Product Rest Api crud Operations",
        description = "Create,Update,Read, Delete Operations for Product Rest Api"
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @Operation(
            summary = "Fetch all Products",
            description = "rest api to fetch all products"
    )
    // GET All Products
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    // CREATE Product
    @Operation(
            summary = " Create Products",
            description = "rest api to create products"
    )
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO, productDTO.getCategoryId());
    }


    // GET Product by ID
    @Operation(
            summary = "Fetch  Products by ID",
            description = "rest api to fetch Products by ID"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // UPDATE Product
    @Operation(
            summary = "Update  Products by ID",
            description = "rest api to update Products by ID"
    )
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO, productDTO.getCategoryId());
    }

    // DELETE Product
    @Operation(
            summary = "Delete  Products by ID",
            description = "rest api to Delete Products by ID"
    )
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
