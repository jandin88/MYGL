package org.github.jandin88.mygl.service;

import com.nimbusds.oauth2.sdk.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.github.jandin88.mygl.controller.exception.CustomException;
import org.github.jandin88.mygl.domain.model.Game;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.model.UserGameList;
import org.github.jandin88.mygl.domain.repository.UserGamerListRepository;
import org.github.jandin88.mygl.dto.userGameList.UserGameListRequest;
import org.github.jandin88.mygl.dto.userGameList.UserGameListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameListService {

    @Autowired
    private UserGamerListRepository repository;
    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    public void insertGameToList(UserGameListRequest userGameList, HttpServletRequest request) {
        User user= userService.getUserByRequest(request);
        Game game= gameService.findById(userGameList.gameID());
        UserGameList gameList= new UserGameList(user, game, userGameList);
        repository.save(gameList);
    }


    public List<UserGameListResponse> listGamerUser(HttpServletRequest request) {
        List<UserGameListResponse> userGameList = repository.findByUserId(userService.getUserByRequest(request)
              .getUserID()).orElseThrow(()->
              new CustomException("list to user not found", HttpStatus.NOT_FOUND));
        return userGameList;
    }
}
