/**
 * 
 */
package br.com.carros.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Vitor B.
 *
 */
@Getter
@Setter
@ToString
public class UsuarioDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private LocalDate birthday;

	private String login;

	private String phone;

	private LocalDateTime lastLogin;
	
	private Set<CarroOutputDTO> cars;

}
