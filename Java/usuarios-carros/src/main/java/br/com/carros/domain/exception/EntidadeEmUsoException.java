package br.com.carros.domain.exception;

public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 6164727756406016422L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);						
	}
	
}