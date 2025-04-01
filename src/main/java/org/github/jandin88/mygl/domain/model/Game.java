package org.github.jandin88.mygl.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long gameID;

    @Column(nullable = false, unique = true, length = 100)
    private String title;

    @Lob
    @JdbcTypeCode(Types.LONGVARCHAR)
    private String description;

    @Column(nullable = false, updatable = false)
    @Schema(type = "string", pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @Column(nullable = false, length = 50)
    private String developer;

    @Column(nullable = false, length = 50)
    private String genre;


}
