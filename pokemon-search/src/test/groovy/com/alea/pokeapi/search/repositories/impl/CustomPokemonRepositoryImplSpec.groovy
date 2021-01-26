package com.alea.pokeapi.search.repositories.impl

import com.alea.pokeapi.search.domain.enums.PokemonCategory
import com.alea.pokeapi.search.domain.model.Pokemon
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.ReactiveSelectOperation
import reactor.core.publisher.Flux
import spock.lang.Specification

/**
 * @author altran
 */
class CustomPokemonRepositoryImplSpec extends Specification {

    def "check pokemon query is performed correctly"() {
        given: "field and limit"
          def field = PokemonCategory.HEIGHT
          def limit = 5
        and: "entity template to query"
          def template = Mock(R2dbcEntityTemplate) {
              1 * select(Pokemon) >> Mock(ReactiveSelectOperation.ReactiveSelect) {
                  1 * matching(_) >> Mock(ReactiveSelectOperation.SelectWithQuery) {
                      1 * all() >> Flux.just(new Pokemon())
                  }
              }
          }
        and: "service to test"
          def service = new CustomPokemonRepositoryImpl(template)
        when: "querying"
          def result = service.findTopPokemonByAttribute(field, limit).collectList().block()

        then: "pokemon is obtained correctly"
          notThrown(Exception)
          result != null
          result.size() == 1
    }
}
