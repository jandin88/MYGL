package org.github.jandin88.mygl.domain.model.enuns;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_CLIENT,ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
