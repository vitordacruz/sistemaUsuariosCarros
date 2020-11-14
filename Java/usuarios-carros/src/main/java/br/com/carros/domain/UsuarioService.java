package br.com.carros.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.carros.domain.exception.EntidadeEmUsoException;
import br.com.carros.domain.exception.NegocioException;
import br.com.carros.domain.exception.UsuarioNaoEncontradoException;
import br.com.carros.domain.model.Usuario;
import br.com.carros.domain.repository.UsuarioRepository;
import br.com.carros.util.ConstantesComum;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String MSG_USUARIO_EM_USO = "Usuario de código %d não pode ser removido, pois está em uso";
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			log.info("Usuário tentou salvar um usuário com e-mail já existente.");
			throw new NegocioException(
					messageSource.getMessage("email.ja.existe", null, null), ConstantesComum.ERROR_CODE_EMAIL_JA_EXISTENTE);
		}
		
		Optional<Usuario> usuarioExistenteLogin = usuarioRepository.findByLogin(usuario.getLogin());
		
		if (usuarioExistenteLogin.isPresent() && !usuarioExistenteLogin.get().equals(usuario)) {
			log.info("Usuário tentou salvar um usuário com login já existente.");
			throw new NegocioException(
					messageSource.getMessage("login.ja.existe", null, null), ConstantesComum.ERROR_CODE_LOGIN_JA_EXISTENTE);
		}
		
		return usuarioRepository.save(usuario);
		
		
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			usuarioRepository.deleteById(cozinhaId);
			usuarioRepository.flush();
			log.info("Usuário excluido.");
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(cozinhaId);					
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
						String.format(MSG_USUARIO_EM_USO, cozinhaId));
		}
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		
		return usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new UsuarioNaoEncontradoException(usuarioId));
		
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		
		Usuario usuario = this.buscarOuFalhar(usuarioId);
		
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			log.info("Senha atual não coincide.");
            throw new NegocioException(messageSource.getMessage("senha.atual.nao.coincide.com.senha.usuario", null, null));
        } 
		
		usuario.setPassword(novaSenha);
		
		usuarioRepository.save(usuario);
		
	}
	
}
