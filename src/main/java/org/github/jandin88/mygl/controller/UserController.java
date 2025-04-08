package org.github.jandin88.mygl.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.github.jandin88.mygl.dto.user.UserRequestDto;
import org.github.jandin88.mygl.dto.user.UserResponseDto;
import org.github.jandin88.mygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<UserResponseDto> findAll(HttpServletRequest request){
        return ResponseEntity.ok().body(service.whoami(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findUserID(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDto> findByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(service.findUserName(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(service.findByEmail(email));
    }


    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(HttpServletRequest request,@RequestBody UserRequestDto user){
        var userCreated= service.updateUser(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteUser(HttpServletRequest request, String username){
         service.deleteUser(username, request);
        return ResponseEntity.ok().body("user deleted");
    }

    //    @GetMapping("test")
//    public ResponseEntity<String> all(HttpServletRequest request) {
//        System.out.println(">>> Usuario logado: " + request.getUserPrincipal());
//        return ResponseEntity.ok("rota protegida acessada");
//    }
}
