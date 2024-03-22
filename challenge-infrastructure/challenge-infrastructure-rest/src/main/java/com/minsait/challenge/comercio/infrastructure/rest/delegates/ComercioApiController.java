package com.minsait.challenge.comercio.infrastructure.rest.delegates;

import com.minsait.challenge.comercio.domain.model.Price;
import com.minsait.challenge.comercio.domain.usecases.SearchPricesUseCase;
import com.minsait.challenge.comercio.infrastructure.rest.apimodel.api.PricesApi;
import com.minsait.challenge.comercio.infrastructure.rest.apimodel.model.PriceDetailsDto;
import com.minsait.challenge.comercio.infrastructure.rest.mappers.PriceDetailsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("${openapi.comercio.base-path:}")
public class ComercioApiController implements PricesApi {

    private final SearchPricesUseCase searchPricesUseCase;
    private final PriceDetailsMapper mapper;

    @Override
    public ResponseEntity<PriceDetailsDto> getPriceDetails(
           Integer brandId, Integer productId, LocalDateTime applicationDate) {
        Price price = searchPricesUseCase.searchPrice(brandId, productId, applicationDate);
        return ResponseEntity.ok(mapper.toDto(price));
    }
}
