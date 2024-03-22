package com.minsait.challenge.comercio.infrastructure.rest.mappers;

import com.minsait.challenge.comercio.domain.model.Price;
import com.minsait.challenge.comercio.infrastructure.rest.apimodel.model.PriceDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceDetailsMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "priceId", source = "id")
    PriceDetailsDto toDto(Price price);
}
