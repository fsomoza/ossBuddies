package com.zombiekid.beginner_oss.entitities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RoleEntity {

    public RoleEntity() {
    }

    @Id
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
