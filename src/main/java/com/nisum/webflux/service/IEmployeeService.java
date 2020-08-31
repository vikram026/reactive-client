package com.nisum.webflux.service;

import com.nisum.webflux.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {

	Mono<Employee> save(Employee employee);

	Flux<Employee> getAllEmployee();

}
