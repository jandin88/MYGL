package org.github.jandin88.mygl.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    public String generateToken(UserDetails userDetails){
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String role= userDetails.getAuthorities()
              .stream()
              .map(GrantedAuthority::getAuthority)
              .findFirst()
              .orElse("USER");

        System.out.println("\n\n\n\n "+role);



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
        } catch (Exception e) {
            System.out.println(">>> Token inválido: " + e.getMessage());
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
              .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
              .build()
              .parseClaimsJws(token)
              .getBody()
              .getSubject();
    }

    public String getRole(String token) {
        Claims claims = Jwts.parserBuilder()
              .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
              .build()
              .parseClaimsJws(token)
              .getBody();

        return claims.get("role", String.class);
    }






}
