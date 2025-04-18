package org.github.jandin88.mygl.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.jandin88.mygl.domain.model.enuns.GameStatus;
import org.github.jandin88.mygl.dto.userGameList.UserGameListRequest;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_game_list")
public class UserGameList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameid", nullable = false)
    private Game game;

    private GameStatus status;

    private Integer score;

    private Integer progress;

    @Temporal(TemporalType.DATE)
    private Date  startDate;

    @Temporal(TemporalType.DATE)
    private Date finishDate;


    public UserGameList(User user, Game game, UserGameListRequest userGameList) {
        this.user= user;
        this.game=game;
        this.status= userGameList.status();
        this.score=userGameList.score();
        this.progress=userGameList.progress();
        this.startDate=userGameList.startDate();
        this.finishDate= userGameList.finishDate();
    }
}
