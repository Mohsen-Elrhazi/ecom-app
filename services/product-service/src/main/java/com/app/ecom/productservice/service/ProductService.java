package com.app.ecom.productservice.service;

import com.app.ecom.productservice.dto.product.request.CreateProductRequest;
import com.app.ecom.productservice.dto.product.request.UpdateProductRequest;
import com.app.ecom.productservice.dto.product.response.ProductResponse;
import com.app.ecom.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse create(CreateProductRequest request);
    void delete(Long id);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll();
    ProductResponse update(Long id, UpdateProductRequest request);

}
