package com.gjug.micronaut.data;

import com.gjug.micronaut.domain.Product;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class InMemoryProductRepository implements ProductRepository {

    private final SortedMap<Long, Product> products = new TreeMap<>();

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.containsKey(id) ? Optional.of(products.get(id)) : Optional.empty();
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            Long newKey = products.isEmpty() ? 1L : products.lastKey() + 1L;
            product.setId(newKey);
        }
        products.put(product.getId(), product);
        return product;
    }
}
