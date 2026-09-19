package com.app.ecom.productservice.dto.category.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(

        @NotBlank(message = "le nom est obligatoire")
        String name,

        String description
) {
}
