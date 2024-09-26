package com.zombiekid.beginner_oss.shared.infrastracture.controllers;

import com.zombiekid.beginner_oss.shared.infrastracture.authentication.AuthRequest;
import com.zombiekid.beginner_oss.shared.infrastracture.authentication.AuthResponse;
import com.zombiekid.beginner_oss.shared.infrastracture.authentication.JwtUtil;
import com.zombiekid.beginner_oss.shared.infrastracture.authentication.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
}

