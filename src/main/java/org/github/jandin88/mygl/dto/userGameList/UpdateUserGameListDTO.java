package org.github.jandin88.mygl.dto.userGameList;

import org.github.jandin88.mygl.domain.model.enuns.GameStatus;

public record UpdateUserGameListDTO(GameStatus status, Integer score, Integer progress) {

}
