package com.app.ecom.productservice.controller;

import com.app.ecom.productservice.dto.category.request.CreateCategoryRequest;
import com.app.ecom.productservice.dto.category.request.UpdateCategoryRequest;
import com.app.ecom.productservice.dto.category.response.CategoryResponse;
import com.app.ecom.productservice.dto.common.ApiResponse;
import com.app.ecom.productservice.mapper.CategoryMapper;
import com.app.ecom.productservice.repository.CategoryRepository;
import com.app.ecom.productservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Validated @RequestBody CreateCategoryRequest request){
        CategoryResponse categoryResponse = categoryService.create(request);

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .success(true)
                .message("Category created successfully")
                .data(categoryResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll(){
        List<CategoryResponse> categories = categoryService.getAll();

        ApiResponse<List<CategoryResponse>> response = ApiResponse.<List<CategoryResponse>>builder()
                .success(true)
                .message("Categories retrieved successfully")
                .data(categories)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        categoryService.delete(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Category deleted successfully")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Long id){
        CategoryResponse category = categoryService.getById(id);

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .success(true)
                .message("Category retrieved successfully")
                .data(category)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable Long id, @RequestBody UpdateCategoryRequest request){
        CategoryResponse category = categoryService.update(id, request);

        ApiResponse<CategoryResponse> response = ApiResponse.<CategoryResponse>builder()
                .success(true)
                .message("Category updated successfully")
                .data(category)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
