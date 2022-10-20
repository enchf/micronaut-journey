package com.gjug.micronaut.integration;

import com.gjug.micronaut.domain.Product;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Filter(value = "/products", methods = { HttpMethod.POST })
public class ProductFilter implements HttpServerFilter {

    @Inject
    private ProductPublisher productPublisher;

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        return Flux.from(chain.proceed(request))
                .flatMap(response -> {
                    Optional<Product> product = response.getBody(Product.class);
                    return product.isPresent() ?
                            Flux.from(productPublisher.publishProduct(product.get().getId())).map(id -> response) :
                            Flux.just(response);
                });
    }
}
