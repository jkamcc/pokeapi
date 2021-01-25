package com.alea.pokeapi.search.services;

import reactor.core.publisher.Mono;

/** @author altran */
public interface PokemonImportService {

  /**
   * Inserts in database the pokemons found in the pokeapi accordingly to its pokemon version
   *
   * @param color pokemon version color red, blue, yellow
   */
  Mono<Void> importPokemon(final String color);
}
