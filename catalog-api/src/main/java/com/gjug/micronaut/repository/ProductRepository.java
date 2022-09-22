package com.gjug.micronaut.repository;

import com.gjug.micronaut.domain.Product;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class ProductRepository implements JpaRepository<Product, Long> {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Product mergeAndSave(Product product) {
        product = entityManager.merge(product);
        return save(product);
    }
}
