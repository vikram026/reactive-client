package com.nisum.webflux.service.impl;

import com.nisum.webflux.costants.ClientUtils;
import com.nisum.webflux.model.Fruit;
import com.nisum.webflux.service.IFruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
@Service
@Slf4j
public class FruitService implements IFruitService {
    @Value("${com.server.url}")
    private String url;

    private final WebClient webClient;

    @Autowired
    public FruitService(   	@Qualifier("webclient") final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }



    @Override
    public Flux<Fruit> getAllFruitsByWebClient() {

        return this.webClient.get().uri(url+"/"+ ClientUtils.GET).retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse-> clientResponse.bodyToMono(Map.class).flatMap(e->{
                    log.error(" 4XX error is occurred ");
                    return Mono.error(new Exception());
                }))
                .onStatus(HttpStatus::is5xxServerError, serverResponse->serverResponse.bodyToMono(Map.class).flatMap(e->{
                    log.error("there is 5XX error ");
                    return Mono.error(new Exception());
                }))
                .bodyToFlux(Fruit.class);
    }

    @Override
    public Mono<Fruit> saveFruitsByWebClient(Fruit fruit) {

        return this.webClient.post().uri(url+"/"+ ClientUtils.POST)
                .body(BodyInserters.fromObject(fruit))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse-> clientResponse.bodyToMono(Map.class).flatMap(e->{
                    log.error(" 4XX error is occurred ");
                    return Mono.error(new Exception());
                }))
                .onStatus(HttpStatus::is5xxServerError, serverResponse->serverResponse.bodyToMono(Map.class).flatMap(e->{
                    log.error("there is 5XX error ");
                    return Mono.error(new Exception());
                }))
                .bodyToMono(Fruit.class);
    }
}
