package com.nisum.webflux.model;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private String id;
	private String name;
	private List<Address> address;	
	private  List<Hobby> hobbies;

}
