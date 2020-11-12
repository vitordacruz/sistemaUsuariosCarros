package br.com.carros.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carros.api.model.UsuarioInputModel;
import br.com.carros.api.model.UsuarioInputModelSemSenha;
import br.com.carros.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInputModel usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public Usuario toDomainObject(UsuarioInputModelSemSenha usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInputModelSemSenha usuarioInput, Usuario usuario) {
		modelMapper.map(usuarioInput, usuario);
	}
	
}
