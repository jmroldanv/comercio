package com.minsait.challenge.comercio.application.config;

import com.minsait.challenge.comercio.application.usecases.SearchPricesUseCaseImpl;
import com.minsait.challenge.comercio.domain.ports.persistence.PriceDao;
import com.minsait.challenge.comercio.domain.usecases.SearchPricesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComercioConfiguration {

    @Bean
    SearchPricesUseCase searchPricesUseCase(PriceDao priceDao) {
        return new SearchPricesUseCaseImpl(priceDao);
    }
}
