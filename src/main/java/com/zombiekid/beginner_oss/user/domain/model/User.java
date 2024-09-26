package com.zombiekid.beginner_oss.user.domain.model;

import java.util.Set;
import java.util.UUID;

public class User {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Set<Role> roles;

    public User(String userName, String email, String password, Set<Role> roles, Long id) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
