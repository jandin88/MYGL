package org.github.jandin88.mygl.domain.repository;


import org.github.jandin88.mygl.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);

}
