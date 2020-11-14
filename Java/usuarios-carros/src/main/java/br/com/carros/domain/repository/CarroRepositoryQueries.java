package br.com.carros.domain.repository;

public interface CarroRepositoryQueries {

	boolean placaExiste(String placa, Long usuarioId);
	
}
