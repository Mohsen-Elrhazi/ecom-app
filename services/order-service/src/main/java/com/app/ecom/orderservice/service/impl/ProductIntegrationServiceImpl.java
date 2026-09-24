package com.app.ecom.orderservice.service.impl;

import com.app.ecom.orderservice.client.ProductServiceClient;
import com.app.ecom.orderservice.dto.common.ApiResponse;
import com.app.ecom.orderservice.dto.product.request.DecreaseStockRequest;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;
import com.app.ecom.orderservice.exception.InsufficientStockException;
import com.app.ecom.orderservice.exception.ResourceNotFoundException;
import com.app.ecom.orderservice.service.ProductIntegrationService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductIntegrationServiceImpl implements ProductIntegrationService {
    private final ProductServiceClient productServiceClient;

    @Override
    public ProductResponse getProduct(Long productId) {
        try {
            return productServiceClient.getProductById(productId).getData();
        }catch (FeignException.NotFound ex){
            throw new ResourceNotFoundException("product not found with this id: " + productId);
        }    }

    @Override
    public void decreaseStock(Long productId, DecreaseStockRequest request) {
        try {
            productServiceClient.decreaseStock(productId,request);
        }catch (FeignException.BadRequest ex){
            throw new InsufficientStockException("Insufficient stock for product with this id: " + productId);
        }
    }
}
