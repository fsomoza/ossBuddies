package com.zombiekid.beginner_oss.security;

import com.zombiekid.beginner_oss.entitities.UserEntity;
import com.zombiekid.beginner_oss.repositories.JpaUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    //TODO - CHANGE TO A RANDOM KEY FOR PRODUCTION
    // Generate a secure key for HS512 algorithm
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private int jwtExpirationInMs = 3600000;

    @Autowired
    private JpaUserRepository userRepository;

    public String generateToken(UserDetails userDetails) {
        UserEntity user = userRepository.findByUsername(userDetails.getUsername());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .claim("tokenVersion", user.getTokenVersion())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .setId(UUID.randomUUID().toString()) // Add this line
                .signWith(key)
                .compact();
    }


    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        int tokenVersion = extractTokenVersion(token);
        UserEntity user = userRepository.findByUsername(username);
        return (username.equals(userDetails.getUsername())
                && tokenVersion == user.getTokenVersion()
                && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

    public String extractTokenId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getId();
    }


    public int extractTokenVersion(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("tokenVersion", Integer.class);
    }


}
