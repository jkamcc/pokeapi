package com.alea.pokeapi.search.controllers;

import com.alea.pokeapi.search.constants.EndpointDocs;
import com.alea.pokeapi.search.constants.Endpoints;
import com.alea.pokeapi.search.constants.TopPokemonConstants;
import com.alea.pokeapi.search.domain.dto.PokemonDTO;
import com.alea.pokeapi.search.domain.enums.PokemonCategory;
import com.alea.pokeapi.search.services.TopPokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.constraints.Positive;

/** @author altran */
@Tag(name = "pokemon-top")
@Validated
@RestController
@RequestMapping(Endpoints.POKEMON_TOP)
@RequiredArgsConstructor
public class TopPokemonController {

  private final TopPokemonService topPokemonService;

  @Operation(description = EndpointDocs.CATEGORY_TOP_DESCRIPTION)
  @GetMapping(value = Endpoints.CATEGORY_TOP, produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<PokemonDTO> findTopPokemon(
      @PathVariable("category") PokemonCategory sortField,
      @RequestParam(
              name = TopPokemonConstants.LIMIT_PARAMETER,
              defaultValue = TopPokemonConstants.DEFAULT_LIMIT,
              required = false)
          @Positive
          Integer limit) {
    return topPokemonService.findTopPokemon(sortField, limit);
  }
}
