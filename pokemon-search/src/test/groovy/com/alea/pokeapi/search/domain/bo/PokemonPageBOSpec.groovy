package com.alea.pokeapi.search.domain.bo


import spock.lang.Specification

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor

/**
 * @author altran
 */
class PokemonPageBOSpec extends Specification {
    def "Check all getters and setters and constructor"() {

        when: "Check all getters, setters and constructor"
          assertPojoMethodsFor(PokemonPageBO).quickly()
                  .areWellImplemented()

        then: "No exceptions thrown"
          noExceptionThrown()
    }
}
