package com.alea.pokeapi.search.config

import WebClientConfig
import spock.lang.Specification

/**
 * @author altran
 */
class WebClientConfigSpec extends Specification {

    def "check rest webclient is loaded"() {
        given: "web client config"
          def config = new WebClientConfig()
        when: "creating rest web client"
            String apiUrl = "https://test.com/api/"
            def webclient  = config.pokemonWebClient(apiUrl)
        then: "poke api configuration is correct"
            webclient != null
    }
}
