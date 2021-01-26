package com.alea.pokeapi.search.domain.mapper;

import com.alea.pokeapi.core.constants.ApiConstants;
import com.alea.pokeapi.search.domain.model.Pokemon;
import com.alea.pokeapi.search.domain.bo.PokemonBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/** @author altran */
@Mapper(
    componentModel = ApiConstants.MAPPER_MODEL,
    nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PokemonMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "pokemonId", source = "id")
  Pokemon pokemonBOToPokemon(PokemonBO pokemonBO);
}
