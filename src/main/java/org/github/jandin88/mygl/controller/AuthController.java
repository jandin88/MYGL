package org.github.jandin88.mygl.controller;

import org.github.jandin88.mygl.domain.model.enuns.UserRole;
import org.github.jandin88.mygl.dto.user.LoginRequestDTO;
import org.github.jandin88.mygl.dto.user.TokenResponseDTO;
import org.github.jandin88.mygl.dto.user.UserRequestDto;
import org.github.jandin88.mygl.dto.user.UserResponseDto;
import org.github.jandin88.mygl.security.JwtService;
import org.github.jandin88.mygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    @PostMapping("register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto user){
        var userCreated= service.insertUser(user,UserRole.USER);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping(value = "login", produces = "application/json")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO login){
        String token= jwtService.authenticateAndGenerateToken(login);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("register/dev")
    public ResponseEntity<UserResponseDto> registerDev(@RequestBody UserRequestDto user){
        var userCreated= service.insertUser(user,UserRole.DEV);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
