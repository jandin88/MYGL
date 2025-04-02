package org.github.jandin88.mygl.service;



import jakarta.persistence.EntityNotFoundException;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.model.enuns.UserRole;
import org.github.jandin88.mygl.domain.repository.UsersRepository;
import org.github.jandin88.mygl.dto.user.UserRequestDto;
import org.github.jandin88.mygl.dto.user.UserResponseDto;
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


    public UserResponseDto insertUser(UserRequestDto userDto, UserRole role){
        String encoder=passwordEncoder.encode(userDto.password());
        User user = new User(userDto.username(),userDto.email(),encoder, role);
        return new UserResponseDto(repository.save(user));
    }

//    public TokenResponseDTO login(LoginRequestDTO user){
//        return  new TokenResponseDTO();
//    }

    public UserResponseDto updateUser(UserRequestDto userUpdated){
        User userToBeUpdate= repository.findUserByEmail(userUpdated.email())
              .orElseThrow(()-> new EntityNotFoundException("Unable to update user: user not found in system"));

        userToBeUpdate.setPassword(userUpdated.password());
        userToBeUpdate.setUsername(userUpdated.username());
        userToBeUpdate.setEmail(userUpdated.email());
        return new UserResponseDto(repository.save(userToBeUpdate));
    }

    public UserResponseDto deleteUser(Long id){
        User deleted= findUserId(id);
        repository.delete(deleted);
        return new UserResponseDto(deleted);
    }

    public List<UserResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getUserID))
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto findUserID(Long id) {
        return new UserResponseDto(findUserId(id));
    }

    public UserResponseDto findUserName(String username) {
        return new UserResponseDto(
                repository.findUserByUsername(username
                ).orElseThrow(()->new NoSuchElementException("Userename not found "+username)));
    }
    public UserResponseDto findByEmail(String email) {
        return new UserResponseDto(
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


