package com.gjug.micronaut.data;

import com.gjug.micronaut.model.Product;
import com.gjug.micronaut.model.Purchase;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product addProduct(Long productId);
    void registerPurchase(Long productId, Purchase purchase);
    Optional<Product> productDetails(Long id);
    List<Product> productList();
    List<Purchase> productPurchases(Long productId);
}
