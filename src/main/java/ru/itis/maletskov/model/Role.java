package ru.itis.maletskov.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, SINGER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
