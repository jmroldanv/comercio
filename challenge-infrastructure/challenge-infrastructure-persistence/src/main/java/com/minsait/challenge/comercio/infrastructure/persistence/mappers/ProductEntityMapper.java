package com.minsait.challenge.comercio.infrastructure.persistence.mappers;

import com.minsait.challenge.comercio.domain.model.Product;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productEntity);
}
