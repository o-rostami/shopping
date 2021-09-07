package com.energizeglobal.shopping.repository;

import com.energizeglobal.shopping.model.entity.CommentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends BaseRepository<CommentEntity, Long> {

    CommentEntity findByBodyAndCreatedBy(String body, String createdBy);
}
