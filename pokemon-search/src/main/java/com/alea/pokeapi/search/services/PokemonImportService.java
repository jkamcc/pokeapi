package com.alea.pokeapi.search.services;

import reactor.core.publisher.Mono;

/** @author altran */
public interface PokemonImportService {

  /**
   * Inserts in database the pokemons found in the pokeapi accordingly to its pokemon version
   *
   * @param limit pokemon
   * @param limit version color
   */
  Mono<Void> importPokemon(int limit, String color);
}
