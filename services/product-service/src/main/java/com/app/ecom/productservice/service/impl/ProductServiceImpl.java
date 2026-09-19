package com.app.ecom.productservice.service.impl;

import com.app.ecom.productservice.dto.product.request.CreateProductRequest;
import com.app.ecom.productservice.dto.product.request.UpdateProductRequest;
import com.app.ecom.productservice.dto.product.response.ProductResponse;
import com.app.ecom.productservice.entity.Category;
import com.app.ecom.productservice.entity.Product;
import com.app.ecom.productservice.exception.ResourceNotFoundException;
import com.app.ecom.productservice.mapper.ProductMapper;
import com.app.ecom.productservice.repository.CategoryRepository;
import com.app.ecom.productservice.repository.ProductRepository;
import com.app.ecom.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductResponse create(CreateProductRequest request) {
        Product product = productMapper.toEntity(request);

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " +request.categoryId()));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " +id));

        productRepository.delete(product);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " +id));

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " +id));

        if(request.categoryId()!= null){
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " +request.categoryId()));

            product.setCategory(category);
        }

         productMapper.updateEntity(request, product);
        Product savedProduct =  productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }
}
