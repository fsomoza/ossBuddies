package com.zombiekid.beginner_oss.security;

import com.zombiekid.beginner_oss.entitities.UserEntity;
import com.zombiekid.beginner_oss.models.User;
import com.zombiekid.beginner_oss.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JpaUserRepository userRepository;

    // Load user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //TODO
        // CUBRIR LA CASUISTICA EN LA QUE EXISTE MAS DE UN USUARIO CON EL MISMO NOMBRE
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Map roles to GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), authorities);
    }
}
