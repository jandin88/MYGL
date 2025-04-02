package org.github.jandin88.mygl.config;

import lombok.RequiredArgsConstructor;
import org.github.jandin88.mygl.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/register/dev")
                          .hasRole("DEV")
                    .requestMatchers(
//                          "auth/register/dev",
                          "/swagger-ui/**",
                          "/v3/api-docs/**",
                          "/",
                          "/auth/login",
                          "/auth/register"
                          )
                    .permitAll()

                    .anyRequest().authenticated()
              )
              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder)
          throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(userDetailsService)
              .passwordEncoder(encoder)
              .and()
              .build();
    }

//    private final JwtAuthenticationFilter jwtFilter;
//    private final UserDetailsService userDetailsService;
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder encoder)
//          throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//              .userDetailsService(userDetailsService)
//              .passwordEncoder(encoder)
//              .and()
//              .build();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT = stateless
//              .and()
//              .authorizeHttpRequests()
//              .requestMatchers("/auth/**").permitAll()
//              .anyRequest().authenticated()
//              .and()
//              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }


}
