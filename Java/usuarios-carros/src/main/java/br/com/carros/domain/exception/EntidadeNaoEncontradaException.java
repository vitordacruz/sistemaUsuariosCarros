package br.com.carros.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
		
	private static final long serialVersionUID = 131255741509868377L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
