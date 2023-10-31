package com.retmix.volga.shared.models;


public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return this.name;
    }

    public boolean roleType() {
        return this.name.equals(ROLE_ADMIN.name());
    }
}
