package br.com.carros.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carros.api.dto.UsuarioInputDTO;
import br.com.carros.api.dto.UsuarioInputDTOSemSenha;
import br.com.carros.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInputDTO usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public Usuario toDomainObject(UsuarioInputDTOSemSenha usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInputDTOSemSenha usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
	
}
