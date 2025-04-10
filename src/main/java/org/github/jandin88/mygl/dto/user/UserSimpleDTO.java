package org.github.jandin88.mygl.dto.user;

import org.github.jandin88.mygl.domain.model.User;

public record UserSimpleDTO(Long id, String name) {
    public UserSimpleDTO(User user) {
        this(user.getUserID(), user.getUsername());
    }
}
