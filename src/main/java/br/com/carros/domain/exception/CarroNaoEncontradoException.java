package br.com.carros.domain.exception;

public class CarroNaoEncontradoException extends EntidadeNaoEncontradaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3219763102568461583L;
	
	private static final String MSG_CARRO_NAO_ENCONTRADO = "Carro não encontrado com o código %d";
	
	public CarroNaoEncontradoException(String mensagem) {
		super(mensagem);
		
	}
	
	public CarroNaoEncontradoException(Long carroId) {
		
		this(String.format(MSG_CARRO_NAO_ENCONTRADO, carroId));
		
	}

}
