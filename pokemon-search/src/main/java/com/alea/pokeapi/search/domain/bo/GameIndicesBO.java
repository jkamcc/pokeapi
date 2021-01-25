package com.alea.pokeapi.search.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameIndicesBO {
  private int gameIndex;
  private VersionBO versionBO;
}
