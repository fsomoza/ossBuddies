package com.zombiekid.beginner_oss.security;

public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

