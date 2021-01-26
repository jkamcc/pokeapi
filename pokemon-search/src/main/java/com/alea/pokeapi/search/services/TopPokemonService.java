package com.alea.pokeapi.search.services;

import com.alea.pokeapi.search.domain.dto.PokemonDTO;
import com.alea.pokeapi.search.domain.enums.PokemonCategory;
import com.alea.pokeapi.search.domain.mapper.PokemonMapper;
import com.alea.pokeapi.search.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/** @author altran */
@Slf4j
@Service
@RequiredArgsConstructor
public class TopPokemonService {

  private final PokemonRepository repository;

  private final PokemonMapper mapper;

  /**
   * @param pokemonCategory
   * @param limit
   * @return
   */
  public Flux<PokemonDTO> findTopPokemon(PokemonCategory pokemonCategory, final int limit) {
    return repository.findTopPokemonByAttribute(pokemonCategory, limit).map(mapper::pokemonToDTO);
  }
}
