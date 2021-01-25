package com.alea.pokeapi.search.config;

import com.alea.pokeapi.search.services.impl.PokemonImportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** @author altran */
@Component
@RequiredArgsConstructor
public class DbLoaderAppRunner implements ApplicationRunner {

  private final PokemonImportServiceImpl pokemonImportService;

  @Value("${pokemon.color}")
  private String pokemonColor;

  @Value("${pokemon.limit}")
  private Integer limit;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    pokemonImportService.importPokemon(limit, pokemonColor).subscribe();
  }
}
