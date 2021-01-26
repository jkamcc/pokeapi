package com.alea.pokeapi.search.domain.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameIndicesBO {
  @JsonProperty("game_index")
  private int gameIndex;
  private VersionBO version;
}
