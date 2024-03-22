package com.minsait.challenge.comercio.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record Price(
        Long id,
        Brand brand,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Product product,
        Integer priority,
        BigDecimal price,
        String currency) {}
