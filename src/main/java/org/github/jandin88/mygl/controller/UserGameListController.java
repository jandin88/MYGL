package org.github.jandin88.mygl.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.github.jandin88.mygl.domain.model.Game;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.model.UserGameList;
import org.github.jandin88.mygl.dto.userGameList.UserGameListRequest;
import org.github.jandin88.mygl.dto.userGameList.UserGameListResponse;
import org.github.jandin88.mygl.service.GameService;
import org.github.jandin88.mygl.service.UserGameListService;
import org.github.jandin88.mygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mygame")
public class UserGameListController {

    @Autowired
    private UserGameListService service;



    @PostMapping("/addgame")
    public ResponseEntity<String> addGameToList(HttpServletRequest request, @RequestBody UserGameListRequest userGameList){
            service.insertGameToList(userGameList, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Game added to user list");
    }

    @GetMapping()
    public ResponseEntity<List<UserGameListResponse>> listUserGame(HttpServletRequest request){
        return ResponseEntity.ok().body(  service.listGamerUser(request));
    }



}
