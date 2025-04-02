package org.github.jandin88.mygl.security;

import org.github.jandin88.mygl.dto.user.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    UserDetailsService userDetailsService;


    public String authenticateAndGenerateToken(LoginRequestDTO loginRequest){
        Authentication authentication= authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails= userDetailsService.loadUserByUsername(loginRequest.username());
        return  tokenProvider.generateToken((userDetails));
    }


//    private Key getSignKey(){
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }

//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
//                .signWith(SignatureAlgorithm.HS256, getSignKey())
//                .compact();
//    }

//    private Key getSignKey() {
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }
}
