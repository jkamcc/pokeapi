package com.alea.pokeapi.search.config.converters;

import com.alea.pokeapi.search.domain.enums.PokemonCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SortPokemonEnumConverter implements Converter<String, PokemonCategory> {

  @Override
  public PokemonCategory convert(String value) {
    return PokemonCategory.valueOf(value.toUpperCase());
  }
}
