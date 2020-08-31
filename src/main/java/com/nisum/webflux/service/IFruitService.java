package com.nisum.webflux.service;

import com.nisum.webflux.model.Fruit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFruitService {

    Flux<Fruit> getAllFruitsByWebClient();
    Mono<Fruit> saveFruitsByWebClient(Fruit fruit);



}
