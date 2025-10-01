package com.bullet.product.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Category",
        description = "It holds Category information"
)
public class CategoryDTO {

    private Long id;
    private String name;
    private String description; // matches Category entity
    private List<ProductDTO> products;
}
