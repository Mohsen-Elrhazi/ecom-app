package com.app.ecom.productservice.kafka.consumer;

import com.app.ecom.productservice.dto.product.request.DecreaseStockRequest;
import com.app.ecom.productservice.kafka.event.DecreaseStockEvent;
import com.app.ecom.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockEventConsumer {

    private final ProductService productService;

    @KafkaListener(
            topics = "stock.decrease",
            groupId = "product-service"
    )
    public void consume(DecreaseStockEvent event) {

        System.out.println(
                "Decrease stock event received: " + event
        );

        productService.decreaseStock(
                event.productId(),
                new DecreaseStockRequest(event.quantity())
        );
    }
}
