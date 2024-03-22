package com.minsait.challenge.comercio.infrastructure.persistence.config;

import com.minsait.challenge.comercio.domain.ports.persistence.PriceDao;
import com.minsait.challenge.comercio.infrastructure.persistence.PriceDaoImpl;
import com.minsait.challenge.comercio.infrastructure.persistence.mappers.PriceEntityMapper;
import com.minsait.challenge.comercio.infrastructure.persistence.repositories.PriceEntityRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.minsait.challenge.comercio.infrastructure.persistence.entities")
@EnableJpaRepositories(
        basePackages = "com.minsait.challenge.comercio.infrastructure.persistence.repositories")
public class JPAInfrastructureConfiguration {

    @Bean
    PriceDao priceDao(PriceEntityRepository repository, PriceEntityMapper mapper) {
        return new PriceDaoImpl(repository, mapper);
    }
}
