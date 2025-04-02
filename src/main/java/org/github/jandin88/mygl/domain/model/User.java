package org.github.jandin88.mygl.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.github.jandin88.mygl.domain.model.enuns.UserRole;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;



    public User(String username, String email, String password, UserRole role){
        this.username=username;
        this.email=email;
        this.password=password;
        this.createdAt=LocalDateTime.now();
        this.role=role;
    }

    public User() {
    }

//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//    }



}
