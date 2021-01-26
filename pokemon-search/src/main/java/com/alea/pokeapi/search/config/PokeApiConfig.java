package com.alea.pokeapi.search.config;

import com.alea.pokeapi.core.config.YmlPropertySourceFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/** @author altran */
@Configuration
@EnableEurekaClient
@PropertySource(value = "classpath:pokeapi.yml", factory = YmlPropertySourceFactory.class)
public class PokeApiConfig {}
