package com.alea.pokeapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @author altran */
@Table("pokedata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

  @Id private Long pokemonId;
  private String name;
  private Integer height;
  private Integer weight;
  private Integer baseExperience;
}
