package org.github.jandin88.mygl.security;

import org.springframework.stereotype.Component;

import java.util.Base64;

public class JwtTokenProvider {

    private String secretKey;

    private long validityToken;

    public JwtTokenProvider(){
        this.secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    
}
