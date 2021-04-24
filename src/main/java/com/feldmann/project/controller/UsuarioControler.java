package com.feldmann.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.feldmann.project.domain.Usuario;
import com.feldmann.project.services.UsuarioService;

@RestController
@RequestMapping(value ="/usuarios")
public class UsuarioControler {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Long id){
		Usuario usr = usuarioService.buscar(id);
		return ResponseEntity.ok().body(usr);
		
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Void> inserir(@RequestBody @Valid Usuario obj){
		obj = usuarioService.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}
