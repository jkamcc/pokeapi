package com.alea.pokeapi.search.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import spock.lang.Specification

/**
 * @author altran
 */
class DatabaseConfigSpec extends Specification {

    def "check database configuration is loaded with database populator"() {
        given: "configuration"
          def config = new DatabaseConfig()
        when: "initialized"
          ConnectionFactoryInitializer initializer = config.initializer(Mock(ConnectionFactory.class))
        then: "memory db loaded config enabled"
          notThrown(Exception)
          initializer != null
          initializer.databasePopulator != null
    }
}
