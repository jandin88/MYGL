package org.github.jandin88.mygl.dto.Game;

import org.github.jandin88.mygl.domain.model.Game;

public record GameSImplesDTO(Long id, String title) {

    public GameSImplesDTO(Game game){
        this(game.getGameID(), game.getTitle());
    }
}
