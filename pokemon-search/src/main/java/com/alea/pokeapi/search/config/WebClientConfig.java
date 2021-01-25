package com.alea.pokeapi.search.config;

import com.alea.pokeapi.core.config.YmlPropertySourceFactory;
import com.alea.pokeapi.core.services.ConnectorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/** @author altran */
@Configuration
@PropertySource(value = "classpath:pokeapi.yml", factory = YmlPropertySourceFactory.class)
public class WebClientConfig {

  @Bean
  WebClient pokemonWebClient(@Value("pokeapi.url") final String pokeApiUrl) {
    return WebClient.builder()
        .baseUrl(pokeApiUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Bean
  ConnectorService pokeApiConnector(WebClient pokemonWebClient) {
    return new ConnectorService(pokemonWebClient);
  }
}
