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
import com.feldmann.project.domain.feign.EnderecoVia;
import com.feldmann.project.services.EnderecoService;
import com.feldmann.project.services.UsuarioService;
import com.feldmann.project.services.ViacepService;

@RestController
@RequestMapping(value ="/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ViacepService viacepService;
	
	@PostMapping("/inserir/{id}")
	public ResponseEntity<Void> inserir(@RequestBody @Valid  Endereco end,@PathVariable Long id){
		
		Usuario usr = usuarioService.buscar(id);
		EnderecoVia enderV= viacepService.buscarCep(end.getCep());
		
		
			end = new Endereco(null,(enderV.getLogradouro().equals("")) ? end.getLogradouro() : enderV.getLogradouro(),
					(enderV.getLogradouro().equals("") || enderV.getNumero()==null) ?  end.getNumero() : enderV.getNumero(),
							end.getComplemento(),
							(enderV.getBairro().equals("")) ? end.getBairro() : enderV.getBairro(),
					enderV.getLocalidade(),enderV.getUf(),enderV.getCep(),enderV.getDdd(),enderV.getIbge(),usr);
			
			end = enderecoService.inserir(end);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
					buildAndExpand(end.getId()).toUri();
			
		return ResponseEntity.created(uri).build();
		
	}

}
