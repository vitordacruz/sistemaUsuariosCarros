package br.com.carros.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.carros.domain.model.Carro;
import br.com.carros.domain.model.Usuario;


public interface CarroRepository extends CustomJpaRepository<Carro, Long>, CarroRepositoryQueries {
	
	List<Carro> findByUsuario(Usuario usuario);
	Optional<Carro> findByLicensePlate(String licensePlate);
	List<Carro> findAllByUsuarioLogin(String login);
	Optional<Carro> findOneByIdAndUsuarioLogin(Long id, String login);
	
}
