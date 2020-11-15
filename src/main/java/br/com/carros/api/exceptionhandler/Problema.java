package br.com.carros.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Problema {	
	
	private String message;
	private Integer errorCode;
	
}
