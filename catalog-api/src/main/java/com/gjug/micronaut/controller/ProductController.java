package com.gjug.micronaut.controller;

import com.gjug.micronaut.domain.Product;
import com.gjug.micronaut.repository.ProductRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
@Controller("/product")
@Transactional
public class ProductController {

    @Inject
    private ProductRepository productRepository;

    @Get(uri="/?page={page}&pagesize={pageSize}", produces="application/json")
    public Page<Product> findAll(int page, int pageSize) {
        return productRepository.findAll(Pageable.from(page, pageSize));
    }

    @Get(uri="/{id}", produces="application/json")
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Post(uri="/", produces="application/json")
    public Product save(Product product) {
        return productRepository.mergeAndSave(product);
    }
}