package com.minsait.challenge.comercio.infrastructure.persistence.mappers;

import com.minsait.challenge.comercio.domain.model.Brand;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {

    BrandEntity toEntity(Brand brand);

    Brand toDomain(BrandEntity brandEntity);
}
