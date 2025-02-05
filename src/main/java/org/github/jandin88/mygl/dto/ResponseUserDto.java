package org.github.jandin88.mygl.dto;

import org.github.jandin88.mygl.domain.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public record ResponseUserDto(Long id, String username, String email, LocalDateTime createdAt) {
    public ResponseUserDto(User user) {
        this(user.getUserID(), user.getUsername(),user.getEmail(),user.getCreatedAt());
    }


}