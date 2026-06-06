package com.cokcok.backend.adapter.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {

        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        (session) -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity
                .authorizeHttpRequests((url) -> url
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated());

        httpSecurity
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        httpSecurity
                .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}