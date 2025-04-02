package org.github.jandin88.mygl.service;

import org.github.jandin88.mygl.domain.model.UserGameList;
import org.github.jandin88.mygl.domain.repository.UserGamerListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGameListService {

    @Autowired
    private UserGamerListRepository repository;

    public UserGameList insertGameToList(UserGameList entity){
        return repository.save(entity);
    }




}
