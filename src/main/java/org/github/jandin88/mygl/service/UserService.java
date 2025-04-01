package org.github.jandin88.mygl.service;



import jakarta.persistence.EntityNotFoundException;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.repository.UsersRepository;
import org.github.jandin88.mygl.dto.RequestUserDto;
import org.github.jandin88.mygl.dto.ResponseUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;
    private final PasswordEncoder passwordEncoder;


    public ResponseUserDto insertUser(RequestUserDto userDto){
        String encoder=passwordEncoder.encode(userDto.password());
        User user = new User(userDto.username(),userDto.email(),encoder);
        return new ResponseUserDto(repository.save(user));
    }

    public ResponseUserDto updateUser(RequestUserDto userUpdated){
        User userToBeUpdate= repository.findUserByEmail(userUpdated.email())
              .orElseThrow(()-> new EntityNotFoundException("Unable to update user: user not found in system"));

        userToBeUpdate.setPassword(userUpdated.password());
        userToBeUpdate.setUsername(userUpdated.username());
        userToBeUpdate.setEmail(userUpdated.email());
        return new ResponseUserDto(repository.save(userToBeUpdate));
    }

    public ResponseUserDto deleteUser(Long id){
        User deleted= findUserId(id);
        repository.delete(deleted);
        return new ResponseUserDto(deleted);
    }

    public List<ResponseUserDto> findAll() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getUserID))
                .map(ResponseUserDto::new)
                .collect(Collectors.toList());
    }

    public ResponseUserDto findUserID(Long id) {
        return new ResponseUserDto(findUserId(id));
    }

    public ResponseUserDto findUserName(String username) {
        return new ResponseUserDto(
                repository.findUserByUsername(username
                ));
    }
    public ResponseUserDto findByEmail(String email) {
        return new ResponseUserDto(
              repository.findUserByEmail(email)
                    .orElseThrow(()-> new EntityNotFoundException("User does not exist "+email)));
    }


    private User findUserId(Long id){
        return  repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Not found Id"+id));
    }

    private UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


}


