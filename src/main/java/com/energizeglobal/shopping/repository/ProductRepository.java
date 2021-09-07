package com.energizeglobal.shopping.repository;

import com.energizeglobal.shopping.model.entity.CategoryEntity;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

    ProductEntity findByNameAndCategory(String name, CategoryEntity categoryEntity);

}
