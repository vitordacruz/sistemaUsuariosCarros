package br.com.carros.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carros.api.dto.CarroInputDTO;
import br.com.carros.domain.model.Carro;

@Component
public class CarroDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Carro toDomainObject(CarroInputDTO carroInput) {
		return modelMapper.map(carroInput, Carro.class);
	}
	
	public void copyToDomainObject(CarroInputDTO carroInput, Carro carro) {
		modelMapper.map(carroInput, carro);
	}
	
}
