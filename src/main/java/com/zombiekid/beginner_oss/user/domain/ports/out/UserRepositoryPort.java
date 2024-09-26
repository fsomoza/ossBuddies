package com.zombiekid.beginner_oss.user.domain.ports.out;

import com.zombiekid.beginner_oss.user.domain.model.User;

import java.util.UUID;

public interface UserRepositoryPort {
    User findByUsername(String username);
    User save(User user);
    String findRoleById(String id);
}
