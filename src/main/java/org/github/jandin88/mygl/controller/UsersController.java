package org.github.jandin88.mygl.controller;

import io.swagger.v3.oas.annotations.Operation;

import org.github.jandin88.mygl.domain.model.Users;
import org.github.jandin88.mygl.dto.CreatUserDto;
import org.github.jandin88.mygl.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping
    @Operation(summary = "create new user")
    public ResponseEntity<Users> createdUser(@RequestBody CreatUserDto user){
        var userCreated= service.insertUser(user);

        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public Users findAll(){
        return new Users();
    }





}
