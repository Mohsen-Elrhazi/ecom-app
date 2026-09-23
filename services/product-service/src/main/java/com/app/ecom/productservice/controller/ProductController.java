package com.app.ecom.productservice.controller;

import com.app.ecom.productservice.dto.category.response.CategoryResponse;
import com.app.ecom.productservice.dto.common.ApiResponse;
import com.app.ecom.productservice.dto.product.request.CreateProductRequest;
import com.app.ecom.productservice.dto.product.request.DecreaseStockRequest;
import com.app.ecom.productservice.dto.product.request.UpdateProductRequest;
import com.app.ecom.productservice.dto.product.response.ProductResponse;
import com.app.ecom.productservice.repository.ProductRepository;
import com.app.ecom.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Validated @RequestBody CreateProductRequest request){
        ProductResponse product = productService.create(request);

        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product created successfully")
                .data(product)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        productService.delete(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Product deleted successfully")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id){
        ProductResponse product = productService.getById(id);

        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product retrieved successfully")
                .data(product)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll(){
        List<ProductResponse> products = productService.getAll();

        ApiResponse<List<ProductResponse>> response = ApiResponse.<List<ProductResponse>>builder()
                .success(true)
                .message("Products retrieved successfully")
                .data(products)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @RequestBody UpdateProductRequest request){
        ProductResponse product = productService.update(id, request);

        ApiResponse<ProductResponse> response = ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product updated successfully")
                .data(product)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/decrease-stock")
    public ResponseEntity<ApiResponse<Void>> decreaseStock(@PathVariable Long id,@RequestBody DecreaseStockRequest request){
        productService.decreaseStock(id, request);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("stock updated successfully")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
