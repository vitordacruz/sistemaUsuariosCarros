package br.com.carros.domain.exception;

public class NegocioException extends RuntimeException {
		
	private static final long serialVersionUID = 131255741509868377L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException (String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
