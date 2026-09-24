package com.app.ecom.orderservice.client;

import com.app.ecom.orderservice.dto.common.ApiResponse;
import com.app.ecom.orderservice.dto.product.request.DecreaseStockRequest;
import com.app.ecom.orderservice.dto.product.response.ProductResponse;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable Long id);

    @PostMapping("/api/products/{id}/decrease-stock")
    void decreaseStock(@PathVariable Long id, @RequestBody DecreaseStockRequest request);
}
