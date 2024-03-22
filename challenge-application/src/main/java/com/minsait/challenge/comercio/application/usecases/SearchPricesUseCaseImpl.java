package com.minsait.challenge.comercio.application.usecases;

import com.minsait.challenge.comercio.domain.exception.BusinessErrorType;
import com.minsait.challenge.comercio.domain.exception.BusinessException;
import com.minsait.challenge.comercio.domain.model.Price;
import com.minsait.challenge.comercio.domain.ports.persistence.PriceDao;
import com.minsait.challenge.comercio.domain.usecases.SearchPricesUseCase;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchPricesUseCaseImpl implements SearchPricesUseCase {

    private final PriceDao priceDao;

    @Override
    public Price searchPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        Optional<Price> price = priceDao.findPrice(brandId, productId, applicationDate);
        return price.orElseThrow(
                () -> new BusinessException(BusinessErrorType.NOT_FOUND, "Price not found"));
    }
}
