package com.alea.pokeapi.search.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** @author altran */
@RequiredArgsConstructor
public enum PokemonCategory {
  HEIGHT("height"),
  WEIGHT("weight"),
  BASE_EXPERIENCE("baseExperience");

  @Getter private final String value;
}
