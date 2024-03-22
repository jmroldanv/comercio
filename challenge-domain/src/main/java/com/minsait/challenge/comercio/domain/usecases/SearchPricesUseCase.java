package com.minsait.challenge.comercio.domain.usecases;

import com.minsait.challenge.comercio.domain.model.Price;
import java.time.LocalDateTime;

public interface SearchPricesUseCase {
    Price searchPrice(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
