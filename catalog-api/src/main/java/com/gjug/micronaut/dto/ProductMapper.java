package com.gjug.micronaut.dto;

import com.gjug.micronaut.common.EntityMapper;
import com.gjug.micronaut.domain.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jsr330", uses = {})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Override
    public Product toEntity(ProductDTO dto) {

        return null;
    }

    @Override
    public ProductDTO toDto(Product entity) {
        return null;
    }

    @Override
    public List<Product> toEntity(List<ProductDTO> dtoList) {
        return null;
    }

    @Override
    public List<ProductDTO> toDto(List<Product> entityList) {
        return null;
    }
}
