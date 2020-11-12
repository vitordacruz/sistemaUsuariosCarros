package br.com.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carros.domain.exception.EntidadeEmUsoException;
import br.com.carros.domain.exception.UsuarioNaoEncontradoException;
import br.com.carros.domain.model.Usuario;
import br.com.carros.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private static final String MSG_USUARIO_EM_USO = "Usuario de código %d não pode ser removido, pois está em uso";
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
		
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			usuarioRepository.deleteById(cozinhaId);
			usuarioRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(cozinhaId);					
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
						String.format(MSG_USUARIO_EM_USO, cozinhaId));
		}
	}
	
	public Usuario buscaOuFalhar(Long usuarioId) {
		
		return usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new UsuarioNaoEncontradoException(usuarioId));
		
	}
	
}
