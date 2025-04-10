package org.github.jandin88.mygl.dto.userGameList;


import org.github.jandin88.mygl.domain.model.Game;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.model.UserGameList;
import org.github.jandin88.mygl.domain.model.enuns.GameStatus;
import org.github.jandin88.mygl.dto.Game.GameSImplesDTO;
import org.github.jandin88.mygl.dto.user.UserResponseDto;
import org.github.jandin88.mygl.dto.user.UserSimpleDTO;

import java.util.Date;

public record UserGameListResponse(Long id, UserSimpleDTO user, GameSImplesDTO game, GameStatus status, Integer score,
                                   Integer progress, Date startDate, Date finishDate) {



    public UserGameListResponse(UserGameList userGameList) {
        this(
              userGameList.getId(),
              new UserSimpleDTO(userGameList.getUser()),
              new GameSImplesDTO(userGameList.getGame()),
              userGameList.getStatus(),
              userGameList.getScore(),
              userGameList.getProgress(),
              userGameList.getStartDate(),
              userGameList.getFinishDate()
        );
    }



}
