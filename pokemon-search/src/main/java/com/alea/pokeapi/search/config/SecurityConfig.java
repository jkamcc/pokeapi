package com.alea.pokeapi.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/** @author altran */
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http.formLogin()
        .disable()
        .httpBasic()
        .disable()
        .authorizeExchange()
        .anyExchange()
        .permitAll()
        .and()
        .build();
  }
}
