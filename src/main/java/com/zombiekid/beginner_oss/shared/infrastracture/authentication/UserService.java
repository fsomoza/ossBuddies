package com.zombiekid.beginner_oss.shared.infrastracture.authentication;

import com.zombiekid.beginner_oss.user.domain.model.Role;
import com.zombiekid.beginner_oss.user.domain.model.User;
import com.zombiekid.beginner_oss.user.infratracture.repositories.JpaUserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private JpaUserRepositoryAdapter userRepository;


    // Register a new user
    public User registerUser(User user) {
        // Encrypt password
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        // Assign default role
        Role userRole = new Role("USER");

        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }
}

