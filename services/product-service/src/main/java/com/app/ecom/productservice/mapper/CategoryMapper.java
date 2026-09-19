package com.app.ecom.productservice.mapper;

import com.app.ecom.productservice.dto.category.request.CreateCategoryRequest;
import com.app.ecom.productservice.dto.category.request.UpdateCategoryRequest;
import com.app.ecom.productservice.dto.category.response.CategoryResponse;
import com.app.ecom.productservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    Category toEntity(CreateCategoryRequest request);
    CategoryResponse toResponse(Category category);
    void updateEntity(UpdateCategoryRequest request, @MappingTarget Category category);
}
