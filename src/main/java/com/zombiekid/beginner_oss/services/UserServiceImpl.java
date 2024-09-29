package com.zombiekid.beginner_oss.services;

import com.zombiekid.beginner_oss.entitities.UserEntity;
import com.zombiekid.beginner_oss.models.Role;
import com.zombiekid.beginner_oss.models.User;
import com.zombiekid.beginner_oss.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {



    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserServiceImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    // Register a new user
    public User registerUser(User user) {
        // Encrypt password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        // Assign default role
        Role userRole = new Role("USER");

        user.setRoles(Collections.singleton(userRole));

        UserEntity userEntity = new UserEntity(user);
        return jpaUserRepository.save(userEntity).toDomainModel();
    }
}

