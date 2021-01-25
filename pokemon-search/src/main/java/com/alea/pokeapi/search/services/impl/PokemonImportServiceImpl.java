package com.alea.pokeapi.search.services.impl;

import com.alea.pokeapi.core.services.ConnectorService;
import com.alea.pokeapi.search.repositories.PokemonRepository;
import com.alea.pokeapi.search.services.PokemonImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/** @author altran */
@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonImportServiceImpl implements PokemonImportService {

  private final ConnectorService pokeApiConnector;

  private final PokemonRepository repository;

  @Value("${pokemon.color}")
  private String color;

  @Override
  public Mono<Void> importPokemon(final String color) {
    return Mono.empty();
  }
}
