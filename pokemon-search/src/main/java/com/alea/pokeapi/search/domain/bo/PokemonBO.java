package com.alea.pokeapi.search.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokemonBO {
  private Integer id;

  @JsonIgnore private String name;

  private Integer height;
  private Integer weight;

  @JsonProperty("base_experience")
  private Integer baseExperience;
}
