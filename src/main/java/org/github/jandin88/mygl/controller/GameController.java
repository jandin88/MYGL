package org.github.jandin88.mygl.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.github.jandin88.mygl.domain.model.Game;
import org.github.jandin88.mygl.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameService service;

    @PostMapping
    public ResponseEntity<Game> insertGame(@RequestBody Game game){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addGame(game));
    }

    @GetMapping
    public ResponseEntity<List<Game>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("title/{title}")
    public ResponseEntity<List<Game>> findTitle(@PathVariable String title){
        return ResponseEntity.ok(service.findAllByTitleLike(title));
    }


    @GetMapping("search/{title}")
    public ResponseEntity<List<Game>> searchGames(@PathVariable String title){
        return ResponseEntity.ok(service.searchByGames(title));
    }

    @GetMapping("search/specific/{name}")
    public ResponseEntity<Game> searchGameSpecific(@PathVariable String name){
        return ResponseEntity.ok(service.findBySpecificNames(name));
    }


    @GetMapping("id/{id}")
    public ResponseEntity<Game> findByIdGame(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("developer/{developer}")
    public ResponseEntity<List<Game>> findByDeveloperGame(@PathVariable String developer){
        return ResponseEntity.ok(service.findByDeveloper(developer));
    }
    @GetMapping("genre/{genre}")
    public ResponseEntity<List<Game>> findByGenre(@PathVariable String genre){
        return ResponseEntity.ok(service.findByGenre(genre));
    }
}
