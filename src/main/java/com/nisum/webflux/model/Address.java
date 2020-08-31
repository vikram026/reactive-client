package com.nisum.webflux.model;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String addressId;
	private String zipCode;
	private List<City> city;

}
