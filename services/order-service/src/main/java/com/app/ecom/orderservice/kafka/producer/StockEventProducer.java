package com.app.ecom.orderservice.kafka.producer;

import com.app.ecom.orderservice.kafka.event.DecreaseStockEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockEventProducer {

    private static final String TOPIC = "stock.decrease";

    private final KafkaTemplate<String, DecreaseStockEvent> kafkaTemplate;

    public void sendDecreaseStockEvent(Long productId, Integer quantity) {

        DecreaseStockEvent event =
                new DecreaseStockEvent(productId, quantity);

        kafkaTemplate.send(
                TOPIC,
                productId.toString(),
                event
        );
    }
}
