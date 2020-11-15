package br.com.carros.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarroOutputDTO {
	private Integer year;	
	private String licensePlate;	
	private String model;	
	private String color;
}
