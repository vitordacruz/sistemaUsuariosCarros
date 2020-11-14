/**
 * 
 */
package br.com.carros.api.dto;

import javax.validation.constraints.NotBlank;

import br.com.carros.domain.model.Usuario;
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
public class UserLoginDTO {

	@NotBlank
	private String login;

	@NotBlank
	private String password;
	
	public static Usuario convertToTO(UserLoginDTO dto) {
		Usuario user = new Usuario();
		user.setLogin(dto.getLogin());
		user.setPassword(dto.getPassword());
		return user;
	}
}
