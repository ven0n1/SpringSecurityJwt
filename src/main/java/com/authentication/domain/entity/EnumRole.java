package com.authentication.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum EnumRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
