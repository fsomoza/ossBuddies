package com.zombiekid.beginner_oss.user.infratracture.entities;

import com.zombiekid.beginner_oss.user.domain.model.Role;
import com.zombiekid.beginner_oss.user.domain.model.User;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(String name, String email, String password, Set<RoleEntity> roles, Long id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.id = id;
    }

    public User toDomainModel() {
        Set<Role> roles = new java.util.HashSet<>();

        this.roles.forEach(role -> {
            Role roleModel = new Role();
            roleModel.setName(role.getName());
            roles.add(roleModel);
        });

        return new User(name, email, password, roles, id);
    }

    public UserEntity(User user) {
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(role -> {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(role.getName());
            return roleEntity;
        }).collect(Collectors.toSet());
        this.id = user.getId();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
