package org.github.jandin88.mygl.controller;


import org.github.jandin88.mygl.domain.model.UserGameList;
import org.github.jandin88.mygl.service.UserGameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserGameListController {

    @Autowired
    private UserGameListService service;

    @PostMapping("my-games")
    public ResponseEntity<String> addGameToList(@RequestBody UserGameList userGameList){
        service.insertGameToList(userGameList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Game added to user list");
    }



}
