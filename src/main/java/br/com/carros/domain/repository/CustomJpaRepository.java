package br.com.carros.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<T, I> extends JpaRepository<T, I> {

	Optional<T> buscarPrimeiro();
	Optional<T> buscarPorId(I id);
	void detach(T entity);
	
}
