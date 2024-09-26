package com.zombiekid.beginner_oss.user.domain.model;

public class Role {

    public Role() {
    }
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
