package org.github.jandin88.mygl.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;


@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    System.out.println("Auth header: "+ authHeader);
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);

        if (jwtTokenProvider.tokenIsValid(token)) {
            String username = jwtTokenProvider.getUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String role = jwtTokenProvider.getRole(token);
            if (role != null && !role.isBlank()) {
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                System.out.println("Authorities do userDetails: " + userDetails.getAuthorities());

                UsernamePasswordAuthenticationToken authentication =
                      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("⚠️ Role ausente no token!");
            }

            System.out.println(">>> JWT válido! Usuário autenticado: " + username +" ROle "+ jwtTokenProvider.getRole(token));
        } else {
            System.out.println(">>> Token inválido!");
        }
    } else {
        System.out.println(">>> Nenhum Authorization header encontrado");
    }

    filterChain.doFilter(request, response);
}

}
