package com.zombiekid.beginner_oss.controllers;

import com.zombiekid.beginner_oss.entitities.UserEntity;
import com.zombiekid.beginner_oss.repositories.JpaUserRepository;
import com.zombiekid.beginner_oss.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private JpaUserRepository userRepository;

    // Authenticate user
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        String jwt;
        try {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authRequest.getUsername());

            jwt = jwtUtil.generateToken(userDetails);
        }catch (Exception e) {
            throw new Exception("User not found", e);
        }


        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    //TODO
    //TENGO QUE PONERLE ESTE NOMBRE PARA QUE NO SEA EL MISMO QUE EL DE SPRING
    @PostMapping("api/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            // Increment tokenVersion in the database
            UserEntity user = userRepository.findByUsername(username);
            user.incrementTokenVersion();
            userRepository.save(user);
        }
        return ResponseEntity.ok("Logged out successfully");
    }


}

