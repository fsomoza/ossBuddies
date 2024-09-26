package com.zombiekid.beginner_oss.user.infratracture.repositories;

import com.zombiekid.beginner_oss.user.infratracture.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, String> {
}
