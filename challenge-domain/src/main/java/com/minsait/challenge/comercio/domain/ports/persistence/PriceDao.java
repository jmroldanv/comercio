package com.minsait.challenge.comercio.domain.ports.persistence;

import com.minsait.challenge.comercio.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceDao {

    Optional<Price> findPrice(long brandId, long productId, LocalDateTime date);
}
