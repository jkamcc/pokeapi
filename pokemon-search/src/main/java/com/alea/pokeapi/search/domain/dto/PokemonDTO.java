package com.alea.pokeapi.search.domain.dto;

import lombok.Data;

/** @author altran */
@Data
public class PokemonDTO {
  private Long pokemonId;
  private String name;
  private Integer height;
  private Integer weight;
  private Integer baseExperience;
}
