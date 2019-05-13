package ru.itis.maletskov.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, SINGER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
