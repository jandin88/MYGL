package org.github.jandin88.mygl.dto.user;

import org.github.jandin88.mygl.domain.model.User;

import java.time.LocalDateTime;

public record UserResponseDto(Long id, String username, String email, LocalDateTime createdAt) {
    public UserResponseDto(User user) {
        this(user.getUserID(), user.getUsername(),user.getEmail(),user.getCreatedAt());
    }


}