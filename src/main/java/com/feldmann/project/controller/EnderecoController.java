package com.feldmann.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@PostMapping("/inserir/{id}")
	public ResponseEntity<Void> inserir(@RequestBody @Valid  Endereco end,@PathVariable Long id){
		
		Usuario usr = usuarioService.buscar(id);
		
		end = new Endereco(null,end.getLogradouro(),end.getNumero(),end.getComplemento(),end.getBairro(),
				end.getCidade(),end.getEstado(),end.getCep(),usr);
		end = enderecoService.inserir(end);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(end.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
