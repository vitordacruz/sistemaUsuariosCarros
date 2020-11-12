package br.com.carros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.api.model.UsuarioInputModel;
import br.com.carros.api.model.UsuarioInputModelSemSenha;
import br.com.carros.assembler.UsuarioAssembler;
import br.com.carros.assembler.UsuarioInputDisassembler;
import br.com.carros.domain.UsuarioDTO;
import br.com.carros.domain.UsuarioService;
import br.com.carros.domain.model.Usuario;
import br.com.carros.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@GetMapping
	public List<UsuarioDTO> listarTodos() {
		return usuarioAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscar(@PathVariable Long usuarioId) {
		return usuarioAssembler.toModel(usuarioService.buscaOuFalhar(usuarioId));
	}
	
	@PostMapping()
	public UsuarioDTO adicionar(@RequestBody UsuarioInputModel usuarioInput) {
		
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		
		return usuarioAssembler.toModel(usuarioService.salvar(usuario));
		
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@RequestBody UsuarioInputModelSemSenha usuarioInput, @PathVariable Long usuarioId) {
		
		Usuario usuarioAtual = usuarioService.buscaOuFalhar(usuarioId);
		
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		
		return usuarioAssembler.toModel(usuarioService.salvar(usuarioAtual));
		
		
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) {
		usuarioService.excluir(usuarioId);
	}
	
}
