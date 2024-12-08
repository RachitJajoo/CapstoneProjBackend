package com.Chubb.EcommerceWebSite.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(req -> req.disable()) // Disable CSRF if not needed for simplicity
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow unauthenticated access
                );

        return http.build();
    }
}
