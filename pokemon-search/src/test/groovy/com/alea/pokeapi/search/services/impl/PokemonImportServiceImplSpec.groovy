package com.alea.pokeapi.search.services.impl

import com.alea.pokeapi.domain.model.Pokemon
import com.alea.pokeapi.search.domain.bo.GameIndicesBO
import com.alea.pokeapi.search.domain.bo.PokemonBO
import com.alea.pokeapi.search.domain.bo.VersionBO
import com.alea.pokeapi.search.domain.mapper.PokemonMapper
import com.alea.pokeapi.search.repositories.PokemonRepository
import com.alea.pokeapi.search.services.PokemonApiService
import reactor.core.publisher.Flux
import spock.lang.Specification

/**
 * @author altran
 */
class PokemonImportServiceImplSpec extends Specification {

    def "import pokemon successfully"() {
        given: "pokemon from api"
          def pokemonList = [
                  new PokemonBO(
                          id: 1l,
                          height: 10,
                          weight: 12,
                          baseExperience: 15,
                          gameIndices: [new GameIndicesBO(1, new VersionBO("red"))] as List),
                  new PokemonBO(
                          id: 2l,
                          height: 11,
                          weight: 12,
                          baseExperience: 15,
                          gameIndices: [new GameIndicesBO(1, new VersionBO("red"))] as List)

          ] as List
        and: "connector is called 1 time an retrieve successfully pokemon data"
          def connector = Mock(PokemonApiService) {
              1 * getPokemonByVersionAndLimit(*_) >> Flux.fromIterable(pokemonList)
          }
        and: "mapper"
          def mapper = Mock(PokemonMapper) {
              2 * pokemonBOToPokemon(_) >> new Pokemon()
          }

        and: "repository pokemon saved once with all items"
          def pokemon = new Pokemon()
          def repository = Mock(PokemonRepository) {
              1 * saveAll(_) >> Flux.just(pokemon)
          }

        and: "service to test"
          def service = new PokemonImportServiceImpl(connector, repository, mapper)

        when: "importing pokemon data"
          service.importPokemon(151, "red").block()
        then: "service calls and all interactions expected are done"
          notThrown(Exception)
    }
}
