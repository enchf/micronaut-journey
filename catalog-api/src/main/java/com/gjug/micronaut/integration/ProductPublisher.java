package com.gjug.micronaut.integration;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import reactor.core.publisher.Mono;

@KafkaClient
public interface ProductPublisher {
    @Topic("products")
    Mono<Long> publishProduct(Long productId);
}
