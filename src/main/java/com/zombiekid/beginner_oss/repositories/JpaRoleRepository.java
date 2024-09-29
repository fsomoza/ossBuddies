package com.zombiekid.beginner_oss.repositories;

import com.zombiekid.beginner_oss.entitities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, String> {
}
