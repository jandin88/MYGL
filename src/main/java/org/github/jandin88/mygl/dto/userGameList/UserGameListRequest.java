package org.github.jandin88.mygl.dto.userGameList;

import org.github.jandin88.mygl.domain.model.enuns.GameStatus;

import java.util.Date;

public record UserGameListRequest(Long gameID, GameStatus status, Integer score,
                                  Integer progress, Date startDate, Date finishDate) {
}
