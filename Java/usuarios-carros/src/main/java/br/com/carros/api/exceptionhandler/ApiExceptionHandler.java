package br.com.carros.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.carros.ConstantesComum;
import br.com.carros.domain.exception.EntidadeNaoEncontradaException;
import br.com.carros.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {
	
	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
	        + "Tente novamente e se o problema persistir, entre em contato "
	        + "com o administrador do sistema.";

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();

		Problema problema = Problema.builder().message(detail).errorCode(ConstantesComum.ERROR_CODE_ENTIDADE_NAO_ENCONTRADA)
				.build();

		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);

	}	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		
		Integer errorCode;
		
		if (e.getErrorCode() == null) {
			errorCode = ConstantesComum.ERROR_CODE_NEGOCIO;
		} else {
			errorCode = e.getErrorCode();
		}

		Problema problema = Problema.builder()
					.message(detail).errorCode(errorCode)
				.build();

		return handleExceptionInternal(e, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problema.builder().message(MSG_ERRO_GENERICA_USUARIO_FINAL).errorCode(ConstantesComum.ERROR_CODE_ENTIDADE_NAO_ENCONTRADA)
					.build();
		} else if (body instanceof String) {
			body = Problema.builder().message((String) body).errorCode(ConstantesComum.ERROR_CODE_ENTIDADE_NAO_ENCONTRADA)
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String detail = String.format("O recurso %s, que você tentou acessar, é inexistente", ex.getRequestURL());
		
		Problema problema = Problema.builder().
						message(detail).errorCode(ConstantesComum.ERROR_CODE_RECURSO_NAO_ENCONTRADO)
					.build();
		
		return handleExceptionInternal(ex, problema, headers, status, request);
		
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
	    String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

	    ex.printStackTrace();
	    
	    Problema problem = Problema.builder().message(detail)
	    			.errorCode(ConstantesComum.ERROR_CODE_ENTIDADE_NAO_ENCONTRADA)
				.build();

	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
}
