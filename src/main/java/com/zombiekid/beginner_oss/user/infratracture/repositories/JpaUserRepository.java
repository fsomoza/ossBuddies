package com.zombiekid.beginner_oss.user.infratracture.repositories;

import com.zombiekid.beginner_oss.user.infratracture.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.name = :username")
    UserEntity findByUsername(String username);
}
