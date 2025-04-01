package org.github.jandin88.mygl.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.impl.Base64Codec;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private String secretKey;

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
