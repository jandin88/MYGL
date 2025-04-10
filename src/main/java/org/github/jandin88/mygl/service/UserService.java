package org.github.jandin88.mygl.service;



import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.github.jandin88.mygl.controller.exception.CustomException;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.model.enuns.UserRole;
import org.github.jandin88.mygl.domain.repository.UsersRepository;
import org.github.jandin88.mygl.dto.user.UserRequestDto;
import org.github.jandin88.mygl.dto.user.UserResponseDto;
import org.github.jandin88.mygl.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;


    public String login(String userName, String password){
        Authentication authentication= authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userName,password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails= userDetailsService.loadUserByUsername(userName);
        return  tokenProvider.generateToken((userDetails));
    }


    public String signup(UserRequestDto userDto, UserRole role){
        String encoder=passwordEncoder.encode(userDto.password());
        User user = new User(userDto.username(),userDto.email(),encoder, role);
        repository.save(user);

        return tokenProvider.generateToken(userDetailsService.loadUserByUsername(userDto.username()));

    }

    public UserResponseDto whoami(HttpServletRequest request) {
        return  new UserResponseDto(getUserByRequest(request));
    }

    public UserResponseDto updateUser(UserRequestDto userUpdated,HttpServletRequest request){

        User userToBeUpdate= getUserByRequest(request);

        userToBeUpdate.setPassword(userUpdated.password());
        userToBeUpdate.setUsername(userUpdated.username());
        userToBeUpdate.setEmail(userUpdated.email());
        return new UserResponseDto(repository.save(userToBeUpdate));
    }

    public void deleteUser(String username, HttpServletRequest request){
        User deleted= getUserByRequest(request);
        if(Objects.equals(deleted.getUsername(), username)) {
            repository.delete(deleted);
            new UserResponseDto(deleted);
        }else {
            throw new CustomException("invalid code",HttpStatus.BAD_REQUEST);
        }
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

    public User getUserByRequest(HttpServletRequest request){
        return repository.findUserByUsername(tokenProvider.getUsername(tokenProvider.resolveToken(request))
        ).orElseThrow(()->new NoSuchElementException("Username not found "));
    }

    private User findUserId(Long id){
        return  repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Not found Id"+id));
    }

    private UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}


