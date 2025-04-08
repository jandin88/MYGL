package org.github.jandin88.mygl.security.userDetails;

import org.github.jandin88.mygl.controller.exception.CustomException;
import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.repository.UsersRepository;
import org.github.jandin88.mygl.security.userDetails.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userRepository.findUserByUsername(username)
              .orElseThrow(() -> new CustomException("Not found user"+ username, HttpStatus.NOT_FOUND));

        return new MyUserDetails(user);
    }

}
