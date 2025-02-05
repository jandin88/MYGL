package org.github.jandin88.mygl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Desativa CSRF (opcional)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/index.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permite acesso ao Swagger e à página inicial
//                        .anyRequest().authenticated() // Bloqueia outras rotas sem autenticação
//                )
//                .formLogin(login -> login.disable()) // Desativa formulário de login
//                .httpBasic(basic -> basic.disable()); // Desativa autenticação básica
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Libera Swagger
                    .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // Apenas admin
                    .requestMatchers("/public/**").permitAll() // Qualquer um pode acessar /public
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // Forma correta no Spring Security 6.1+

    return http.build();
}

}
