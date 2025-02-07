package org.github.jandin88.mygl.domain.repository;


import org.github.jandin88.mygl.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Game> searchPartialTile(String title);

    Game findGameByTitleIgnoreCase(String title);

    List<Game> findGameByDeveloperIgnoreCase(String developer);

    List<Game> findGameByGenreIgnoreCase(String genre);


    @Query(value = "SELECT g.*, similarity(g.title, :title) AS similarity_score " +
            "FROM tb_game g " +
            "ORDER BY similarity(g.title, :title) DESC " +  // Correção: Ordenar de forma decrescente
            "LIMIT 10",
            nativeQuery = true)
    List<Game> searchTitleGames(String title);
}
