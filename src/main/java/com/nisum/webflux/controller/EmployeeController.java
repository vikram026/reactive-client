package com.nisum.webflux.controller;

import com.nisum.webflux.costants.ClientUtils;
import com.nisum.webflux.model.Employee;
import com.nisum.webflux.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;


	@PostMapping(ClientUtils.EMPLOYEE)
	public Mono<Employee> save(@RequestBody
                                       Employee employee) {
		log.info("employee obj "+employee);
		return employeeService.save(employee);
	}
	
	
	@GetMapping(ClientUtils.EMPLOYEE)
	public Flux<Employee> getAll(){
		return employeeService.getAllEmployee();
	}

}
