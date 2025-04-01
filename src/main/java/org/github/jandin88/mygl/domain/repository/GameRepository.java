package org.github.jandin88.mygl.domain.repository;


import org.github.jandin88.mygl.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Optional<List<Game>> searchPartialTile(String title);

    Optional<Game> findGameByTitleIgnoreCase(String title);

    Optional<List<Game>> findGameByDeveloperContainingIgnoreCase(String developer);

    Optional<List<Game>> findByGenreContainingIgnoreCase(String genre);


    @Query(value = "SELECT g.*, similarity(g.title, :title) AS similarity_score " +
            "FROM tb_game g " +
            "ORDER BY similarity(g.title, :title) DESC " +  // Correção: Ordenar de forma decrescente
            "LIMIT 10",
            nativeQuery = true)
    Optional<List<Game>> searchTitleGames(String title);
}
