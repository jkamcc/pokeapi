package com.alea.pokeapi.search.services;

import com.alea.pokeapi.core.services.ConnectorService;
import com.alea.pokeapi.search.domain.bo.PokemonBO;
import com.alea.pokeapi.search.domain.bo.PokemonPageBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** @author altran */
@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonApiService {

  private final ConnectorService connector;

  @Value("${pokeapi.endpoints.getPokemon}")
  private String pokemonUrl;

  /**
   * Returns Flux of pokemon given a limit count and game version
   *
   * @param limit quantity of pokemon to search
   * @param color game version
   * @return pokemon elements
   */
  public Flux<PokemonBO> getPokemonByVersionAndLimit(final int limit, final String color) {
    return connector
        .doCall(
            uriBuilder -> uriBuilder.path(pokemonUrl).queryParam("limit", limit).build(),
            PokemonPageBO.class)
        .map(PokemonPageBO.class::cast)
        .flatMapIterable(PokemonPageBO::getResults)
        .flatMap(
            result ->
                getPokemon(result.getName(), color)
                    .flatMap(pokemon -> filterByColorVersion(color, pokemon)));
  }

  /**
   * @param name pokemon name
   * @param color pokemon version: red, blue, yellow
   * @return given pokemon if exists in the version requested
   */
  public Mono<PokemonBO> getPokemon(final String name, final String color) {
    return connector
        .doCall(
            uriBuilder -> uriBuilder.path(pokemonUrl).pathSegment("{name}").build(name),
            PokemonBO.class)
        .map(
            obj -> {
              PokemonBO pokemonBO = (PokemonBO) obj;
              pokemonBO.setName(name);
              return pokemonBO;
            })
        .flatMap(pokemonBO -> filterByColorVersion(color, pokemonBO));
  }

  /** Checks the version of the current pokemon and returns it if success */
  private Mono<PokemonBO> filterByColorVersion(
      final String colorVersion, final PokemonBO pokemonBO) {
    return Flux.fromIterable(pokemonBO.getGameIndices())
        .filter(index -> index.getVersion().getName().equalsIgnoreCase(colorVersion))
        .collectList()
        .filter(list -> !list.isEmpty())
        .map(version -> pokemonBO);
  }
}
