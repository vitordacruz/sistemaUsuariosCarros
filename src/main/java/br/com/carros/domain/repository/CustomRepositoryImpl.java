package br.com.carros.domain.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRepositoryImpl<T, I> extends SimpleJpaRepository<T, I> implements CustomJpaRepository<T, I> {

	private EntityManager manager;
	
	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		var jpql = "from " + getDomainClass().getName();
		
		T entity = manager.createQuery(jpql, getDomainClass())
			.setMaxResults(1)
			.getSingleResult();
		
		return Optional.ofNullable(entity);
		
	}

	@Override
	public Optional<T> buscarPorId(I id) {		
		
		T entity = manager.find(getDomainClass(), id);
		
		return Optional.ofNullable(entity);
		
	}

	@Override
	public void detach(T entity) {
		manager.detach(entity);		
	}

}
