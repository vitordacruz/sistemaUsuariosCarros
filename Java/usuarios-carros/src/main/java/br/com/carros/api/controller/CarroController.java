package br.com.carros.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.api.dto.CarroOutputDTO;
import br.com.carros.assembler.CarroAsssembler;
import br.com.carros.domain.CarroService;
import br.com.carros.domain.model.Carro;

@RestController
@RequestMapping("/api/cars")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@Autowired
	private CarroAsssembler carroAssembler;
	
	@GetMapping
	public List<CarroOutputDTO> findAllCarros(Principal principal) {
		
		List<Carro> carros = carroService.findAllByUsuarioLogin(principal.getName());
		
		return carroAssembler.toCollectionModel(carros);
		
	}
	
	@GetMapping("/{carroId}")
	public CarroOutputDTO findById(@PathVariable Long carroId, Principal principal) {
		 return carroAssembler.toModel(carroService.buscarOuFalhar(carroId, principal.getName()));
	}
	
}
