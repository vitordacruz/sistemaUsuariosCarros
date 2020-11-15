package br.com.carros.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carros.api.dto.UsuarioOutputDTO;
import br.com.carros.domain.model.Usuario;

@Component
public class UsuarioAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioOutputDTO toModel(Usuario usuario) {
		
		return modelMapper.map(usuario, UsuarioOutputDTO.class);
		
	}
	
	public List<UsuarioOutputDTO> toCollectionModel(Collection<Usuario> usuarios) {
		
		return usuarios.stream()
					.map(usuario -> toModel(usuario))
					.collect(Collectors.toList());
		
	}
	
}