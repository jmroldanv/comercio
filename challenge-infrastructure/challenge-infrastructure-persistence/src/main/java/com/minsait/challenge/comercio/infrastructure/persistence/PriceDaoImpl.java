package com.minsait.challenge.comercio.infrastructure.persistence;

import com.minsait.challenge.comercio.domain.model.Price;
import com.minsait.challenge.comercio.domain.ports.persistence.PriceDao;
import com.minsait.challenge.comercio.infrastructure.persistence.config.CacheConfiguration;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.PriceEntity;
import com.minsait.challenge.comercio.infrastructure.persistence.mappers.PriceEntityMapper;
import com.minsait.challenge.comercio.infrastructure.persistence.repositories.PriceEntityRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
@Slf4j
public class PriceDaoImpl implements PriceDao {

    private final PriceEntityRepository repository;
    private final PriceEntityMapper mapper;

    @Override
    @Cacheable(
            cacheNames = CacheConfiguration.CACHE_NAME,
            key = "#brandId + '-' + #productId + '-' + #date")
    public Optional<Price> findPrice(long brandId, long productId, LocalDateTime date) {
        log.debug(
                "Recuperando precio para cadena {}, producto {} y fecha {}",
                brandId,
                productId,
                date);
        // BrandEntity brand = BrandEntity.builder().id(brandId).build();
        // ProductEntity product = ProductEntity.builder().id(productId).build();
        // Optional<PriceEntity> priceEntity =
        // repository.findFirstByBrandAndProductAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brand, product, date, date);
        Optional<PriceEntity> priceEntity = repository.findPrice(brandId, productId, date);
        return priceEntity.map(mapper::toDomain);
    }
}
