package com.alea.pokeapi.search.repositories;

import com.alea.pokeapi.search.domain.enums.PokemonCategory;
import com.alea.pokeapi.search.domain.model.Pokemon;
import reactor.core.publisher.Flux;

/** @author altran */
public interface CustomPokemonRepository {

  Flux<Pokemon> findTopPokemonByAttribute(PokemonCategory field, int limit);
}
