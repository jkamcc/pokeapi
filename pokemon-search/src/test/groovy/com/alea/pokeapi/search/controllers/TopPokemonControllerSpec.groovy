package com.alea.pokeapi.search.controllers

import com.alea.pokeapi.search.domain.dto.PokemonDTO
import com.alea.pokeapi.search.domain.enums.PokemonCategory
import com.alea.pokeapi.search.services.TopPokemonService
import reactor.core.publisher.Flux
import spock.lang.Specification

/**
 * @author altran
 */
class TopPokemonControllerSpec extends Specification {

    def "find top pokemon by attribute successfully"() {
        given: "field and limit"
          def field = PokemonCategory.HEIGHT
          def limit = 5
        and: "top service"
          def service = Mock(TopPokemonService) {
              1 * findTopPokemon(field, limit) >> Flux.just(PokemonDTO)
          }
        and: "controller to test"
          def controller = new TopPokemonController(service)

        when: "getting top pokemon"
          def result = controller.findTopPokemon(field, limit).collectList().block()
        then: "pokemon are obtained correctly"
          notThrown(Exception)
          result != null
          result.size() == 1
    }
}
