package org.github.jandin88.mygl.domain.repository;

import org.github.jandin88.mygl.domain.model.UserGameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGamerList extends JpaRepository<UserGameList, Long> {

}
