package org.github.jandin88.mygl.controller;

import org.github.jandin88.mygl.domain.model.enuns.UserRole;
import org.github.jandin88.mygl.dto.user.LoginRequestDTO;
import org.github.jandin88.mygl.dto.user.TokenResponseDTO;
import org.github.jandin88.mygl.dto.user.UserRequestDto;
import org.github.jandin88.mygl.dto.user.UserResponseDto;
import org.github.jandin88.mygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService service;


    @PostMapping("signup")
    public ResponseEntity<TokenResponseDTO> signup(@RequestBody UserRequestDto user){
        var token= service.signup(user,UserRole.USER);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping(value = "signin", produces = "application/json")
    public ResponseEntity<TokenResponseDTO> signin(
          @RequestParam String username,
          @RequestParam String password) {

        String token= service.login(username, password);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @PostMapping("signup/dev")
    public ResponseEntity<TokenResponseDTO>  signupDev(@RequestBody UserRequestDto user){
        var token= service.signup(user,UserRole.DEV);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
