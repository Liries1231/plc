package com.example.CodeLC.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    AUTHOR;
    @Override
    public String getAuthority() {
        return name();
    }
}
