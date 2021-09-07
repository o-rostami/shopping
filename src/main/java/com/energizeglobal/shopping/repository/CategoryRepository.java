package com.energizeglobal.shopping.repository;

import com.energizeglobal.shopping.model.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<CategoryEntity, Long> {
}
