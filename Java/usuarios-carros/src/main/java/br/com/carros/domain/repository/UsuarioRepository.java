package br.com.carros.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.carros.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByLogin(String login);
	
	@Query("SELECT u.password FROM Usuario u WHERE login = ?1")
	String findPassword(String username);
	
}
