package com.energizeglobal.shopping.repository;

import com.energizeglobal.shopping.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    UserEntity findByUserNameAndBlockedTrue(String userName);

    UserEntity findByUserName(String userName);
}
