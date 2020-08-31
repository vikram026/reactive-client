package com.nisum.webflux.controller;

import com.nisum.webflux.model.Fruit;
import com.nisum.webflux.service.IFruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FruitController {
    @Autowired
    IFruitService fruitService;


    @GetMapping("/fruit")
    public Flux<Fruit> getAllByWebClient(){
        return fruitService.getAllFruitsByWebClient();
    }

    @PostMapping("/fruit")
    public Mono<Fruit> savetoMongo(@RequestBody
                                           Fruit fruit) {
        return fruitService.saveFruitsByWebClient(fruit);
    }



}
