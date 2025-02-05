package org.github.jandin88.mygl.controller;

import org.github.jandin88.mygl.dto.RequestUserDto;
import org.github.jandin88.mygl.dto.ResponseUserDto;
import org.github.jandin88.mygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<ResponseUserDto> createdUser(@RequestBody RequestUserDto user){
        var userCreated= service.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    @PutMapping
    public ResponseEntity<ResponseUserDto> updateUser(@RequestBody RequestUserDto user){
        var userCreated= service.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUserDto> deleteUser(@PathVariable Long id){
        var userDelete= service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDelete);
    }


    @GetMapping
    public ResponseEntity<List<ResponseUserDto>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findUserID(id));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<ResponseUserDto> findByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(service.findUserName(username));
    }
    @GetMapping("/email{email}")
    public ResponseEntity<ResponseUserDto> findByEmail( @PathVariable String email){
        return ResponseEntity.ok().body(service.findByEmail(email));
    }




}
