package com.app.ecom.productservice.service;

import com.app.ecom.productservice.dto.category.request.CreateCategoryRequest;
import com.app.ecom.productservice.dto.category.request.UpdateCategoryRequest;
import com.app.ecom.productservice.dto.category.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest request);
    List<CategoryResponse> getAll();
    void delete(Long id);
    CategoryResponse getById(Long id);
    CategoryResponse update(Long id, UpdateCategoryRequest request);
}
