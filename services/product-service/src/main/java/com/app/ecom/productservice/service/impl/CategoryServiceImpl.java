package com.app.ecom.productservice.service.impl;

import com.app.ecom.productservice.dto.category.request.CreateCategoryRequest;
import com.app.ecom.productservice.dto.category.request.UpdateCategoryRequest;
import com.app.ecom.productservice.dto.category.response.CategoryResponse;
import com.app.ecom.productservice.entity.Category;
import com.app.ecom.productservice.exception.ResourceAlreadyExistsException;
import com.app.ecom.productservice.exception.ResourceNotFoundException;
import com.app.ecom.productservice.mapper.CategoryMapper;
import com.app.ecom.productservice.repository.CategoryRepository;
import com.app.ecom.productservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {
       if( categoryRepository.existsByName(request.name())){
            throw new ResourceAlreadyExistsException("Category already exists with this name: " + request.name());
        }
        Category category = categoryMapper.toEntity(request);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category not found with this id: " + id));

        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse getById(Long id) {
       Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with this id: " +id));

       return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse update(Long id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with this id: " +id));

        categoryMapper.updateEntity(request, category);

        categoryRepository.save(category);

        return categoryMapper.toResponse(category);
    }
}
