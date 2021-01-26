package com.alea.pokeapi.search.services

import com.alea.pokeapi.core.services.ConnectorService
import com.alea.pokeapi.search.domain.bo.GameIndicesBO
import com.alea.pokeapi.search.domain.bo.PokemonBO
import com.alea.pokeapi.search.domain.bo.PokemonItemBO
import com.alea.pokeapi.search.domain.bo.PokemonPageBO
import com.alea.pokeapi.search.domain.bo.VersionBO
import reactor.core.publisher.Mono
import spock.lang.Specification

/**
 * @author altran
 */
class PokemonApiServiceSpec extends Specification {

    def "retrieve pokemon from api successfully"() {
        given: "pokemon"
          def pokemonBO = new PokemonBO(
                  id: 1l,
                  height: 10,
                  weight: 12,
                  baseExperience: 15,
                  gameIndices: [new GameIndicesBO(1, new VersionBO("red")),
                                new GameIndicesBO(2, new VersionBO("blue"))] as List)
        and: "connector call an retrieve successfully pokemon data"
          def connector = Mock(ConnectorService) {
              1 * doCall(*_) >> Mono.just(pokemonBO)
          }
        and: "service to test"
          def service = new PokemonApiService(connector)
        when: "obtaining pokemon"
          def result = service.getPokemon("bulbasor", "red").block()
        then: "pokemon is retrieved from rest call"
          notThrown(Exception)
          result.name == "bulbasor"
          result.height == 10
          result.weight == 12
          result.baseExperience == 15
    }

    def "check pokemon is not retrieved when does not match version"() {
        given: "pokemon"
          def pokemonBO = new PokemonBO(
                  id: 1l,
                  gameIndices: [new GameIndicesBO(1, new VersionBO("yellow")),
                                new GameIndicesBO(2, new VersionBO("blue"))] as List)
        and: "connector call an retrieve successfully pokemon data"
          def connector = Mock(ConnectorService) {
              1 * doCall(*_) >> Mono.just(pokemonBO)
          }
        and: "service to test"
          def service = new PokemonApiService(connector)
        when: "obtaining pokemon"
          def result = service.getPokemon("bulbasor", "red").block()
        then: "pokemon is retrieved from rest call"
          notThrown(Exception)
          result == null
    }

    def "check pokemon list is retrieved from pokemon page"() {
        given: "pokemon red and other not pokemon red"
          def pokemon1 =
                  new PokemonBO(
                          id: 1l,
                          gameIndices: [new GameIndicesBO(1, new VersionBO("red")),
                                        new GameIndicesBO(2, new VersionBO("blue"))] as List)
          def pokemon2 = new PokemonBO(
                  id: 2l,
                  gameIndices: [new GameIndicesBO(1, new VersionBO("yellow")),
                                new GameIndicesBO(2, new VersionBO("blue"))] as List)
          def pokemon3 = new PokemonBO(
                  id: 3l,
                  gameIndices: [new GameIndicesBO(1, new VersionBO("red")),
                                new GameIndicesBO(2, new VersionBO("blue"))] as List)

        and: "pokemon page returned with 3 elements"
          def pokemonPage = new PokemonPageBO(155, [
                  new PokemonItemBO(name: "charmander"),
                  new PokemonItemBO(name: "pichu"),
                  new PokemonItemBO(name: "charizard")])

        and: "connector call an retrieve successfully pokemon data"
          def connector = Mock(ConnectorService) {
              1 * doCall(_, PokemonPageBO) >> Mono.just(pokemonPage)
              3 * doCall(_, PokemonBO) >> Mono.just(pokemon1) >> Mono.just(pokemon2) >> Mono.just(pokemon3)
          }

        and: "service to test"
          def service = new PokemonApiService(connector)

        when: "obtaining pokemon"
          def result = service.getPokemonByVersionAndLimit(151, "red").collectList().block()

        then: "pokemon is retrieved from rest call"
          notThrown(Exception)
          result != null
          result.size() == 2
    }
}
