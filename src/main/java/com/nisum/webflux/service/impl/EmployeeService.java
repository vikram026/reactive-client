package com.nisum.webflux.service.impl;

import com.nisum.webflux.costants.ClientUtils;
import com.nisum.webflux.model.*;
import com.nisum.webflux.service.IEmployeeService;
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
public class EmployeeService implements IEmployeeService{
	@Value("${com.server.url}")
	private String url;

	//using webclient to hit the server;
	private final WebClient webClient;

	@Autowired
	public EmployeeService(   	@Qualifier("webclient") final WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	@Override
	public Mono<Employee> save(Employee employee) {

		log.info("Into save method");
		return this.webClient.post().uri(url+ ClientUtils.EMPLOYEE)
				.body(BodyInserters.fromObject(employee))
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse-> clientResponse.bodyToMono(Map.class).flatMap(e->{
					log.error(" 4XX error is occurred ");
					return Mono.error(new Exception());
				}))
				.onStatus(HttpStatus::is5xxServerError, serverResponse->serverResponse.bodyToMono(Map.class).flatMap(e->{
					log.error("there is 5XX error ");
					return Mono.error(new Exception());
				}))
				.bodyToMono(Employee.class);
	}

	//using webclient to hit server;

	@Override
	public Flux<Employee> getAllEmployee() {

		return this.webClient.get().uri(url+ ClientUtils.EMPLOYEE).retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse-> clientResponse.bodyToMono(Map.class).flatMap(e->{
					log.error(" 4XX error is occurred ");
					return Mono.error(new Exception());
				}))
				.onStatus(HttpStatus::is5xxServerError, serverResponse->serverResponse.bodyToMono(Map.class).flatMap(e->{
					log.error("there is 5XX error ");
					return Mono.error(new Exception());
				}))
				.bodyToFlux(Employee.class);

	}

}
