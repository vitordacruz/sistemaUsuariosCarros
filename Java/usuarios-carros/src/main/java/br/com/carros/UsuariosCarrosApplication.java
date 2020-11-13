package br.com.carros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.carros.domain.repository.CustomRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class UsuariosCarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosCarrosApplication.class, args);
	}

}
