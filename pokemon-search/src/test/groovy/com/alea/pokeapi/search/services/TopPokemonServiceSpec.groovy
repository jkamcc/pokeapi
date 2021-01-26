package com.alea.pokeapi.search.services

import com.alea.pokeapi.search.domain.enums.PokemonCategory
import com.alea.pokeapi.search.domain.mapper.PokemonMapperImpl
import com.alea.pokeapi.search.domain.model.Pokemon
import com.alea.pokeapi.search.repositories.PokemonRepository
import reactor.core.publisher.Flux
import spock.lang.Specification

/**
 * @author altran
 */
class TopPokemonServiceSpec extends Specification {

    def "obtain top attributes from repository and return dto"() {
        given: "field and limit"
          def field = PokemonCategory.HEIGHT
          def limit = 5

        and: "repository"
          def repository = Mock(PokemonRepository) {
              1 * findTopPokemonByAttribute(field, limit) >> Flux.just(new Pokemon())
          }
        and: "mapper"
          def mapper = new PokemonMapperImpl()
        and: "service to test"
          def service = new TopPokemonService(repository, mapper)
        when: "obtaining top pokemon"
          def result = service.findTopPokemon(field, limit).collectList().block()
        then: "pokemon are obtained correctly"
          notThrown(Exception)
          result != null
          result.size() == 1
    }
}
