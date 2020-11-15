package br.com.carros.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carros.api.dto.UsuarioDTO;
import br.com.carros.domain.model.Usuario;

@Component
public class UsuarioDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioDTO toModel(Usuario usuario) {
		
		return modelMapper.map(usuario, UsuarioDTO.class);
		
	}
	
	public List<UsuarioDTO> toCollectionModel(Collection<Usuario> usuarios) {
		
		return usuarios.stream()
					.map(usuario -> toModel(usuario))
					.collect(Collectors.toList());
		
	}
}
