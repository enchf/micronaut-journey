package com.gjug.micronaut.controller;

import com.gjug.micronaut.data.ProductRepository;
import com.gjug.micronaut.domain.Product;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/products")
public class ProductController {

    @Inject
    private ProductRepository productRepository;

    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<Product>> findAll() {
        return HttpResponse.accepted().body(productRepository.findAll());
    }

    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> findById(Long id) {
        return productRepository.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Product> create(@Body Product product) {
        return HttpResponse.created(productRepository.save(product));
    }
}