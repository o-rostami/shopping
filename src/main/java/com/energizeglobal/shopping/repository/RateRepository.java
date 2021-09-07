package com.energizeglobal.shopping.repository;

import com.energizeglobal.shopping.model.entity.RateEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends BaseRepository<RateEntity, Long> {

    RateEntity findByCreatedByAndProductId(String userName, Long productId);

    @Query(value = "SELECT avg(r.rate) FROM RateEntity r where  r.product.id= :productId")
    Double calculateAverageRate(@Param("productId") Long productId);


}
