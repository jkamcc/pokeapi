package com.alea.pokeapi.search.repositories.impl;

import com.alea.pokeapi.search.domain.enums.PokemonCategory;
import com.alea.pokeapi.search.domain.model.Pokemon;
import com.alea.pokeapi.search.repositories.CustomPokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static org.springframework.data.relational.core.query.Query.query;

/** @author altran */
@Repository
@RequiredArgsConstructor
public class CustomPokemonRepositoryImpl implements CustomPokemonRepository {

  private final R2dbcEntityTemplate entityTemplate;

  /**
   * It is easier to use findByTop10Weight in query templates for the 3 attributes, nevertheless
   * that requires to create 3 methods and more code boilerplate, in the contrary this method is
   * reusable for extensibility if a new attribute is added and accepts dynamic limit changing.
   *
   * <p>Another option is to use Pageable, but in this case wanted to show use of Criteria
   *
   * @param field
   * @param limit
   * @return
   */
  @Override
  public Flux<Pokemon> findTopPokemonByAttribute(PokemonCategory field, int limit) {

    return entityTemplate
        .select(Pokemon.class)
        .matching(query(Criteria.empty()).sort(Sort.by(field.getValue()).descending()).limit(limit))
        .all();
  }
}
