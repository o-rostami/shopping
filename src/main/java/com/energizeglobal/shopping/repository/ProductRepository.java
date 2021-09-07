package com.energizeglobal.shopping.repository;

import com.energizeglobal.shopping.model.entity.CategoryEntity;
import com.energizeglobal.shopping.model.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByNameAndCategory(String name, CategoryEntity categoryEntity);

    Optional<ProductEntity> findByIdAndCategoryId(Long id, Long categoryId);


}
