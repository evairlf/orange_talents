package com.feldmann.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.feldmann.project.domain.Endereco;
import com.feldmann.project.domain.Usuario;
import com.feldmann.project.services.EnderecoService;
import com.feldmann.project.services.UsuarioService;

@RestController
//requisiçao agatetepe
//esse negocio faz com que o front fale com o back
@RequestMapping(value ="/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/inserir")
	public ResponseEntity<Void> inserir(@RequestBody @Valid Usuario usr,@Valid Endereco end){
		
		end = new Endereco(end.getId(),end.getLogradouro(),end.getNumero(),end.getComplemento(),end.getBairro(),end.getCidade(),end.getEstado(),
				end.getCep(),usr);
		usr = new Usuario(usr.getId(),usr.getNome(),usr.getEmail(),usr.getCpf(),usr.getDataDeNascimento());
		
		usr = usuarioService.inserir(usr);
		end = enderecoService.inserir(end);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(end.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
