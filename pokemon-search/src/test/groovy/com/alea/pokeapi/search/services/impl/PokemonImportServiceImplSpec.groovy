package com.alea.pokeapi.search.services.impl

import com.alea.pokeapi.core.services.ConnectorService
import com.alea.pokeapi.domain.model.Pokemon
import com.alea.pokeapi.search.domain.bo.PokemonBO
import com.alea.pokeapi.search.repositories.PokemonRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

/**
 * @author altran
 */
class PokemonImportServiceImplSpec extends Specification {

    def "import pokemon successfully"() {
        given: "connector call an retrieve successfully pokemon data"
          def pokemonBO = new PokemonBO()
          def connector = Mock(ConnectorService) {
              (1.._) * doCall(*_) >> Flux.just(pokemonBO)
          }
        and: "repository pokemon saved at least once"
          def pokemon = new Pokemon()
          def repository = Mock(PokemonRepository) {
              (1.._) * save(_) >> Mono.just(pokemon)
          }
        and: "service to test"
          def service = new PokemonImportServiceImpl(connector, repository)
        when: "importing pokemon data"
          service.importPokemon("red").block()
        then: "service calls and imports correctly"
          notThrown(Exception)
          pokemon.pokemonId == 1L
          pokemon.weight == 12
          pokemon.height == 10
          pokemon.baseExperience == 5
    }
}
