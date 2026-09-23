package com.app.ecom.orderservice.client;

import com.app.ecom.orderservice.dto.common.ApiResponse;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable Long id);
}
