package com.app.ecom.orderservice.service;

import com.app.ecom.orderservice.dto.product.request.DecreaseStockRequest;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;

public interface ProductIntegrationService {
    ProductResponse getProduct(Long productId);
    void decreaseStock(Long productId, DecreaseStockRequest request);
}
