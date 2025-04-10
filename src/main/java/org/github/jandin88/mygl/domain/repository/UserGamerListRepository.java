package org.github.jandin88.mygl.domain.repository;

import org.github.jandin88.mygl.domain.model.UserGameList;
import org.github.jandin88.mygl.dto.userGameList.UserGameListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGamerListRepository extends JpaRepository<UserGameList, Long> {

    @Query("SELECT u FROM UserGameList u WHERE u.user.userID = :userId")
    Optional<List<UserGameListResponse>> findByUserId(@Param("userId") Long userId);
}
