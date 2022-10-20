package com.gjug.micronaut.integration;

import com.gjug.micronaut.data.ProductRepository;
import com.gjug.micronaut.model.Product;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

@KafkaListener
public class ProductListener {
    @Inject
    private ProductRepository repository;

    @Topic("products")
    public Product receiveProduct(Long productId) {
        return repository.addProduct(productId);
    }
}
