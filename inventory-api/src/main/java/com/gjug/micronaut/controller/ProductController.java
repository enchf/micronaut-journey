package com.gjug.micronaut.controller;

import com.gjug.micronaut.data.ProductRepository;
import com.gjug.micronaut.model.Product;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/products")
public class ProductController {

    @Inject
    private ProductRepository repository;

    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<Product>> index() {
        return HttpResponse.ok().body(repository.productList());
    }

    @Get(uri = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Product> findById(Long id) {
        return repository.productDetails(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Post(uri = "/", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Product> addProduct(@Body Long id) {
        return HttpResponse.created(repository.addProduct(id));
    }
}