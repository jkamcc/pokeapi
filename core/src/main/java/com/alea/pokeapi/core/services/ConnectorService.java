package com.alea.pokeapi.core.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.function.Function;

/**
 * @author altran Created this class to provide a generic service across all microservices to
 *     provide a layer which deals with the REST calls implementation, which is in this case
 *     WebClient but could be anything, like RestTemplate, Reactive Feign, etc... with easy-deal
 *     refactoring in case
 */
public class ConnectorService {

  private WebClient webClient;

  public ConnectorService(WebClient webClient) {
    this.webClient = webClient;
  }

  public <T> Flux<T> doCall(Function<UriBuilder, URI> uriFunction, Type type) {
    return doCall(uriFunction, type, HttpMethod.GET, null);
  }

  public <T> Flux<T> doCall(
      Function<UriBuilder, URI> uriFunction, Type type, HttpMethod method, Object body) {

    WebClient.RequestBodySpec clientRequest =
        webClient.method(method).uri(uriFunction).accept(MediaType.APPLICATION_JSON);

    if (body != null) {
      clientRequest.bodyValue(body);
    }
    return clientRequest.retrieve().bodyToFlux(ParameterizedTypeReference.forType(type));
  }
}
