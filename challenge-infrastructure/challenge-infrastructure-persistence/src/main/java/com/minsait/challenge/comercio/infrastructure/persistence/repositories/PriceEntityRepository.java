package com.minsait.challenge.comercio.infrastructure.persistence.repositories;

import com.minsait.challenge.comercio.infrastructure.persistence.entities.BrandEntity;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.PriceEntity;
import com.minsait.challenge.comercio.infrastructure.persistence.entities.ProductEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, Long> {

    @Query(
            "SELECT p FROM PriceEntity p WHERE p.brand.id = :brandId AND p.product.id = :productId"
                + " AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> findPrice(long brandId, long productId, LocalDateTime date);

    Optional<PriceEntity>
            findFirstByBrandAndProductAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                    BrandEntity brand,
                    ProductEntity product,
                    LocalDateTime startDate,
                    LocalDateTime endDate);
}
