package org.github.jandin88.mygl.domain.repository;


import org.github.jandin88.mygl.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
