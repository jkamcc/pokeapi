package com.alea.pokeapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/** @author altran */
@Configuration
public class WebClientConfig {

  @Bean
  WebClient pokemonWebClient(@Value("pokeapi.url") final String pokeApiUrl) {
    return WebClient.builder()
        .baseUrl(pokeApiUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
