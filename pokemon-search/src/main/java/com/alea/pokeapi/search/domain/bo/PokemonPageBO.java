package com.alea.pokeapi.search.domain.bo;

import lombok.Data;

import java.util.List;

@Data
public class PokemonPageBO {
  private int count;
  private List<PokemonItemBO> results;
}
