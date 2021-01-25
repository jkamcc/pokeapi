package com.alea.pokeapi.repositories;

import com.alea.pokeapi.domain.model.Pokemon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/** @author altran */
public interface PokemonRepository extends ReactiveCrudRepository<Pokemon, Long> {}
