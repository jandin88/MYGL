package org.github.jandin88.mygl.dto.user;


import org.github.jandin88.mygl.domain.model.User;

public record UserRequestDto(String username, String email, String password) {


    public UserRequestDto(User user) {
        this(user.getUsername(),user.getEmail(),user.getPassword());
    }
}
