package br.com.carros.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.carros.domain.model.Usuario;
import br.com.carros.domain.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> user = repository.findByLogin(login);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Login not found");
		}

		return user.get();
	}
}
