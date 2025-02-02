package org.github.jandin88.mygl.service;



import org.github.jandin88.mygl.domain.model.Users;
import org.github.jandin88.mygl.domain.repository.UsersRepository;
import org.github.jandin88.mygl.dto.CreatUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;
    private final PasswordEncoder passwordEncoder;


    public Users insertUser(CreatUserDto userDto){
        String encoder=passwordEncoder.encode(userDto.password());
        Users user = new Users(userDto.username(),userDto.email(),encoder);
        return repository.save(user);
    }
    public List<Users>findAll(){

        return  repository.findAll();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UsersService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



}


