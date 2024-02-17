package com.appsdeveloperblog.ws.productservice.service.impl;

import com.appsdeveloperblog.ws.core.events.ProductCreatedEvent;
import com.appsdeveloperblog.ws.productservice.model.CreateProductRestModel;
import com.appsdeveloperblog.ws.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String save(CreateProductRestModel productRestModel) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();
        // TODO: Save the Product
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRestModel.getTitle(),
                productRestModel.getPrice(), productRestModel.getQuantity());
        LOGGER.info("Before publishing product created event");
        SendResult<String, ProductCreatedEvent> sendResult = kafkaTemplate.send("products-created-event-topic", productId, productCreatedEvent).get();
        LOGGER.info("Result metadata, partition: {}, topic: {}, offset: {}", sendResult.getRecordMetadata().partition(),
                sendResult.getRecordMetadata().topic(), sendResult.getRecordMetadata().offset());
        LOGGER.info("Returning ProductId : {} ", productId);
        return productId;
    }
}
