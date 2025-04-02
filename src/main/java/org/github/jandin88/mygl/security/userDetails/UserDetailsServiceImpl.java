package org.github.jandin88.mygl.security.userDetails;

import org.github.jandin88.mygl.domain.model.User;
import org.github.jandin88.mygl.domain.repository.UsersRepository;
import org.github.jandin88.mygl.security.userDetails.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new MyUserDetails(user);
    }

}
