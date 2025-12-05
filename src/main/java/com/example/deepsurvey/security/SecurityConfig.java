package com.example.deepsurvey.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Liberar OPTIONS
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 🔓 Rotas públicas
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/cart/**").permitAll()
                .antMatchers("/orders/**").permitAll()

                // 🔓 Permitir apenas LER as configs da loja
                .antMatchers(HttpMethod.GET, "/store-config").permitAll()

                // 🔒 Qualquer alteração exige ADMIN
                .antMatchers("/store-config/**").hasRole("ADMIN")

                // 🔒 Rotas administrativas gerais
                .antMatchers("/admin/**").hasRole("ADMIN")

                // Qualquer outro endpoint precisa estar autenticado
                .anyRequest().authenticated()
            );

        http.httpBasic().disable();
        http.formLogin().disable();
        http.logout().disable();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}