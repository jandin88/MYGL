package org.github.jandin88.mygl.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.github.jandin88.mygl.controller.exception.CustomException;
import org.github.jandin88.mygl.security.userDetails.MyUserDetails;
import org.github.jandin88.mygl.security.userDetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;


@Component
public class JwtTokenProvider {

    @Value("${JWTSECRETKEYS}")
    private String secretKey;
    @Value("${JWTEXPIRATIONS}")
    private long validityToken=86400000;

    @Autowired
    private UserDetailsServiceImpl myUserDetails;

    public String generateToken(UserDetails userDetails){
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String role= userDetails.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)
              .findFirst()
              .orElse("USER");




        return Jwts.builder()
              .setClaims(Map.of("role", role))
              .setSubject(userDetails.getUsername())
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis() + validityToken))
              .signWith(key)
              .compact();
    }

    public boolean tokenIsValid(String token) {
        try {
            Jwts.parserBuilder()
                  .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                  .build()
                  .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException  e) {
            System.out.println("error no token is valid");
            throw new CustomException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    public String resolveToken(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
              .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
              .build()
              .parseClaimsJws(token)
              .getBody()
              .getSubject();
    }







}
