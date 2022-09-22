package com.gjug.micronaut.controller;

import com.gjug.micronaut.data.ProductRepository;
import com.gjug.micronaut.model.Purchase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/purchases")
public class PurchaseController {

    @Inject
    private ProductRepository repository;

    @Get(uri = "/{productId}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<Purchase>> index(Long productId) {
        return HttpResponse.ok().body(repository.productPurchases(productId));
    }

    @Post(uri = "/{productId}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Purchase> addPurchase(Long productId, @Body Purchase purchase) {
        repository.registerPurchase(productId, purchase);
        return HttpResponse.created(purchase);
    }
}