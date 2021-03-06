package br.com.carros.api.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

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

import br.com.carros.api.dto.CarroInputDTO;
import br.com.carros.api.dto.CarroOutputDTO;
import br.com.carros.assembler.CarroAsssembler;
import br.com.carros.assembler.CarroDisassembler;
import br.com.carros.domain.CarroService;
import br.com.carros.domain.UsuarioService;
import br.com.carros.domain.model.Carro;
import br.com.carros.domain.model.Usuario;

@RestController
@RequestMapping("/api/cars")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CarroAsssembler carroAssembler;
	
	@Autowired
	private CarroDisassembler carroDisassembler;
	
	@GetMapping
	public List<CarroOutputDTO> findAllCarros(Principal principal) {
		
		List<Carro> carros = carroService.findAllByUsuarioLogin(principal.getName());
		
		return carroAssembler.toCollectionModel(carros);
		
	}
	
	@GetMapping("/{carroId}")
	public CarroOutputDTO findById(@PathVariable Long carroId, Principal principal) {
		 return carroAssembler.toModel(carroService.buscarOuFalhar(carroId, principal.getName()));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarroOutputDTO adicionar(@RequestBody @Valid CarroInputDTO carroInput, Principal principal) {
		
		Usuario usuario = usuarioService.findBySession(principal);
		
		Carro carro = carroDisassembler.toDomainObject(carroInput);
		
		carro.setUsuario(usuario);
		
		return carroAssembler.toModel(carroService.salvar(carro));
		
	}
	
	@PutMapping("/{carroId}")
	public CarroOutputDTO atualizar(@RequestBody @Valid CarroInputDTO carroInput, @PathVariable Long carroId,  Principal principal) {
		
		Carro carroAtual = carroService.buscarOuFalhar(carroId);
		
		carroDisassembler.copyToDomainObject(carroInput, carroAtual);
		
		Usuario usuario = usuarioService.findBySession(principal);
		
		carroAtual.setUsuario(usuario);
		
		return carroAssembler.toModel(carroService.salvar(carroAtual));
		
	}
	
	@DeleteMapping("/{carroId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long carroId, Principal principal) {
		
		Usuario usuario = usuarioService.findBySession(principal);
		
		carroService.excluir(carroId, usuario);
		
	}
}
