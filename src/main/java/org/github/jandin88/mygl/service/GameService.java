package org.github.jandin88.mygl.service;


import org.github.jandin88.mygl.domain.model.Game;
import org.github.jandin88.mygl.domain.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public Game addGame(Game game){
        return repository.save(game);
    }


    public List<Game> findAll() {
        return repository.findAll();
    }

    public Game findById(Long id) {
        return repository.findById(id).orElseThrow(()->
              new NoSuchElementException("Not found game "+ id));
    }

    public Game findBySpecificNames(String name) {
//        Game game= repository.findGameByTitleIgnoreCase(name);
//        return gameExists(game,name);

        return  repository.findGameByTitleIgnoreCase(name).orElseThrow(()->
              new NoSuchElementException("Game not found: "+name)
              );
    }

    public List<Game> findAllByTitleLike(String title) {
//        var gameFind= repository.searchPartialTile(title);
//        return gameExists(gameFind,title);

        return repository.searchPartialTile(title).orElseThrow(()->
              new NoSuchElementException("Game not found: "+ title)
              ).stream().sorted(Comparator.comparing(Game::getGameID).reversed())
              .toList();

    }

    public List<Game> searchByGames(String title){
//        var gameFind= repository.searchTitleGames(title);
//        return gameExists(gameFind,title);

        return  repository.searchTitleGames(title).orElseThrow(()->
              new NoSuchElementException("Not found Game:"+title));
    }

    @Transactional
    public List<Game> findByDeveloper(String developer) {
//        return gameExists(repository.findGameByDeveloperContainingIgnoreCase(developer), developer);
        return  repository.findGameByDeveloperContainingIgnoreCase(developer).orElseThrow(()->
              new NoSuchElementException("Not found developer: "+developer));

    }

    @Transactional
    public List<Game> findByGenre(String genre) {
//        return gameExists(repository.findByGenreContainingIgnoreCase(genre), genre);
        return repository.findByGenreContainingIgnoreCase(genre).orElseThrow(()->
              new NoSuchElementException("Not found genre: "+genre));

    }

//    private <T> T gameExists(T game, String search) {
//        if (game == null || (game instanceof List && ((List<?>) game).isEmpty())) {
//            throw new NoSuchElementException("Not found: " + search);
//        }
//        return game;
//    }

}
