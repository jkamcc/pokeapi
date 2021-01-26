package com.alea.pokeapi.search.repositories;

import com.alea.pokeapi.search.domain.model.Pokemon;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/** @author altran */
@Repository
public interface PokemonRepository extends ReactiveCrudRepository<Pokemon, Long> {}
