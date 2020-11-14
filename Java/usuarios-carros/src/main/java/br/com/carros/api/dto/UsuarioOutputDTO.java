package br.com.carros.api.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.carros.domain.model.Carro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioOutputDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String phone;
	private List<Carro> cars;

}
