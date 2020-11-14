package br.com.carros.domain;

import java.util.List;
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
import br.com.carros.domain.model.Carro;
import br.com.carros.domain.repository.CarroRepository;
import br.com.carros.util.ConstantesComum;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	private static final String MSG_CARRO_EM_USO = "Carro de código %d não pode ser removido, pois está em uso";
	
	@Transactional
	public Carro salvar(Carro carro) {
		
		carroRepository.detach(carro);
		
		Optional<Carro> carroExistente = carroRepository.findByLicensePlate(carro.getLicensePlate());
		
		if (carroExistente.isPresent() && !carroExistente.get().equals(carro)) {
			log.info("Já existe um carro com essa placa");
			throw new NegocioException(
					messageSource.getMessage("placa.carro.ja.existe", null, null), ConstantesComum.ERROR_CODE_PLACA_CARRO_JA_EXISTE);
		}
		
		return carroRepository.save(carro);		
		
	}
	
	public boolean placaExiste(String placa, Long usuarioId) {
		return carroRepository.placaExiste(placa, usuarioId);
	}
	
	public Carro buscarOuFalhar(Long carroId) {
		
		return carroRepository.findById(carroId).orElseThrow(
				() -> new UsuarioNaoEncontradoException(carroId));
		
	}
	
	public Carro buscarOuFalhar(Long carroId, String login) {
		
		return carroRepository.findOneByIdAndUsuarioLogin(carroId, login).orElseThrow(
				() -> new UsuarioNaoEncontradoException(carroId));
		
	}
	
	@Transactional
	public void excluir(Long carroId) {
		try {
			carroRepository.deleteById(carroId);
			carroRepository.flush();
			log.info("Usuário excluido.");
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(carroId);					
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
						String.format(MSG_CARRO_EM_USO, carroId));
		}
	}
	
	public List<Carro> findAllByUsuarioLogin(String login) {
		return carroRepository.findAllByUsuarioLogin(login);
	}
	
}
