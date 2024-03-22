package com.minsait.challenge.comercio.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.minsait.challenge.comercio.domain.model.Price;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.PriceEntity;
import com.minsait.challenge.comercio.infrastructure.persistence.mappers.PriceEntityMapper;
import com.minsait.challenge.comercio.infrastructure.persistence.repositories.PriceEntityRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceDaoImplTest {

    @InjectMocks private PriceDaoImpl priceDao;

    @Mock private PriceEntityRepository repository;

    @Mock private PriceEntityMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findPrice_returnsPrice_whenPriceExists() {
        // Given
        long brandId = 1L;
        long productId = 35455L;
        LocalDateTime date = LocalDateTime.now();
        PriceEntity priceEntity = new PriceEntity();
        Price price = Price.builder().id(productId).build();

        when(repository.findPrice(brandId, productId, date)).thenReturn(Optional.of(priceEntity));
        when(mapper.toDomain(priceEntity)).thenReturn(price);

        // when(repository.findFirstByBrandAndProductAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(any(), any(), any(), any()))
        //        .thenReturn(Optional.of(priceEntity));
        // when(mapper.toDomain(priceEntity)).thenReturn(price);

        // When
        Optional<Price> result = priceDao.findPrice(brandId, productId, date);

        // Then
        assertTrue(result.isPresent());
        assertEquals(price, result.get());
    }

    @Test
    public void findPrice_returnsEmpty_whenPriceDoesNotExist() {
        // Given
        long brandId = 1L;
        long productId = 35455L;
        LocalDateTime date = LocalDateTime.now();

        when(repository.findPrice(brandId, productId, date)).thenReturn(Optional.empty());

        // When
        Optional<Price> result = priceDao.findPrice(brandId, productId, date);

        // Then
        assertTrue(result.isEmpty());
    }
}
