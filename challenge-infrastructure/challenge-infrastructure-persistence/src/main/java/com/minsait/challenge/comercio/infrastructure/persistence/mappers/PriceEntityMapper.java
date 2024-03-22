package com.minsait.challenge.comercio.infrastructure.persistence.mappers;

import com.minsait.challenge.comercio.domain.model.Price;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    PriceEntity toEntity(Price price);

    Price toDomain(PriceEntity priceEntity);
}
