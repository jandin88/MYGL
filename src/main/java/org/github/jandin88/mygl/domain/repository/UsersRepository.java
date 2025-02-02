package org.github.jandin88.mygl.domain.repository;


import org.github.jandin88.mygl.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
