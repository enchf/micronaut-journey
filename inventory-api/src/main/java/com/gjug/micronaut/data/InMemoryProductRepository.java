package com.gjug.micronaut.data;

import com.gjug.micronaut.model.Product;
import com.gjug.micronaut.model.Purchase;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Singleton
public class InMemoryProductRepository implements ProductRepository {

    private final SortedMap<Long, List<Purchase>> products = new TreeMap<>();
    private static final BigDecimal EARNING_PERCENTAGE = new BigDecimal("1.3");

    @Override
    public Product addProduct(Long productId) {
        products.putIfAbsent(productId, new ArrayList<>());
        return buildProduct(productId);
    }

    @Override
    public void registerPurchase(Long productId, Purchase purchase) {
        products.get(productId).add(purchase);
    }

    @Override
    public Optional<Product> productDetails(Long id) {
        return products.containsKey(id) ? Optional.of(buildProduct(id)) : Optional.empty();
    }

    private Product buildProduct(Long id) {
        return new Product(id, sumProducts(id), determinePrice(id));
    }

    private int sumProducts(Long id) {
        List<Purchase> purchases = productPurchases(id);
        Map<Boolean, List<Purchase>> partition = purchases.stream().collect(Collectors.partitioningBy(Purchase::getIncome));
        Function<List<Purchase>, Integer> countProducts = list -> list.stream().map(Purchase::getQuantity).reduce(0, Integer::sum);
        return countProducts.apply(partition.get(true)) - countProducts.apply(partition.get(false));
    }

    private BigDecimal determinePrice(Long id) {
        List<BigDecimal> purchases = productPurchases(id).stream().filter(Purchase::getIncome).map(Purchase::getCostPerUnit).collect(Collectors.toList());
        BigDecimal sum = purchases.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return purchases.isEmpty() ? BigDecimal.ZERO : sum.divide(new BigDecimal(purchases.size()), MathContext.UNLIMITED).multiply(EARNING_PERCENTAGE);
    }

    @Override
    public List<Product> productList() {
        return products.keySet().stream().map(this::buildProduct).collect(Collectors.toList());
    }

    @Override
    public List<Purchase> productPurchases(Long productId) {
        return products.get(productId);
    }


}
