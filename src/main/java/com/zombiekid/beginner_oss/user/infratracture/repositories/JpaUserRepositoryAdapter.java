package com.zombiekid.beginner_oss.user.infratracture.repositories;

import com.zombiekid.beginner_oss.user.domain.model.User;
import com.zombiekid.beginner_oss.user.domain.ports.out.UserRepositoryPort;
import com.zombiekid.beginner_oss.user.infratracture.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final JpaRoleRepository jpaRoleRepository;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository, JpaRoleRepository jpaRoleRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public User findByUsername(String username) {

        return jpaUserRepository.findByUsername(username).toDomainModel();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        return jpaUserRepository.save(userEntity).toDomainModel();
    }

    @Override
    public String findRoleById(String id) {
        return jpaRoleRepository.findById(id).get().getName();
    }
}
