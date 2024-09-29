package com.zombiekid.beginner_oss.repositories;

import com.zombiekid.beginner_oss.entitities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.name = :username")
    UserEntity findByUsername(String username);
}
