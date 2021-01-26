package com.alea.pokeapi.search.domain.model

import com.alea.pokeapi.search.domain.model.Pokemon
import spock.lang.Specification

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor

/**
 * @author altran
 */
class PokemonSpec extends Specification {

    def "Check all getters and setters and constructor"() {

        when: "Check all getters, setters and constructor"
          assertPojoMethodsFor(Pokemon).quickly()
                  .areWellImplemented()

        then: "No exceptions thrown"
          noExceptionThrown()
    }

}
