package br.com.carros.api.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carros.api.dto.UserLoginDTO;
import br.com.carros.api.dto.UsuarioDTO;
import br.com.carros.assembler.UsuarioDTOAssembler;
import br.com.carros.domain.UsuarioService;


/**
 * @author Vitor B.
 */
@RestController
@RequestMapping("/api/")
public class AuthResource {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioDTOAssembler usuarioAssembler;

	@PostMapping(path = "/signin")
	public ResponseEntity<String> signin(@Valid @RequestBody UserLoginDTO dto, HttpServletResponse response) {
		String token = service.signin(UserLoginDTO.convertToTO(dto));

		String bearerToken = "Bearer " + token;
		response.addHeader("Authorization", bearerToken);
		response.addHeader("access-control-expose-headers", "Authorization");

		return ResponseEntity.ok(bearerToken);
	}

	@GetMapping(path = "/me")
	public ResponseEntity<UsuarioDTO> me(Principal principal) {
		
		UsuarioDTO usuario = usuarioAssembler.toModel(service.findByLogin(principal.getName()));
		
		return ResponseEntity.ok(usuario);
	}

}