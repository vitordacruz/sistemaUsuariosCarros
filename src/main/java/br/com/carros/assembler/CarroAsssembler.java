package br.com.carros.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carros.api.dto.CarroOutputDTO;
import br.com.carros.domain.model.Carro;

@Component
public class CarroAsssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CarroOutputDTO toModel(Carro carro) {
		
		return modelMapper.map(carro, CarroOutputDTO.class);
		
	}
	
	public List<CarroOutputDTO> toCollectionModel(Collection<Carro> carros) {
		
		return carros.stream()
					.map(carro -> toModel(carro))
					.collect(Collectors.toList());
		
	}
	
}
