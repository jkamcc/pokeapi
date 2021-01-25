package com.alea.pokeapi.search.services.impl;

import com.alea.pokeapi.search.domain.mapper.PokemonMapper;
import com.alea.pokeapi.search.repositories.PokemonRepository;
import com.alea.pokeapi.search.services.PokemonApiService;
import com.alea.pokeapi.search.services.PokemonImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/** @author altran */
@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonImportServiceImpl implements PokemonImportService {

  private final PokemonApiService pokemonApiService;

  private final PokemonRepository repository;

  private final PokemonMapper mapper;

  @Override
  public Mono<Void> importPokemon(final int limit, final String color) {
    return Mono.empty();
  }
}
