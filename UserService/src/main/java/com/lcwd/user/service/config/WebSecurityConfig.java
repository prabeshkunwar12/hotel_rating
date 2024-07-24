package com.lcwd.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security
                .authorizeHttpRequests(
                        requests -> requests
                                .anyRequest()
                                .authenticated())
                .oauth2ResourceServer(
                        server -> server
                                .jwt(
                                        Customizer.withDefaults()))
                .build();
    }
}
