package com.alea.pokeapi.search.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonPageBO {
  private int count;
  private List<PokemonItemBO> results;
}
