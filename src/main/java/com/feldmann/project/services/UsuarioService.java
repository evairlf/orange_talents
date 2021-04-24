package com.feldmann.project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.feldmann.project.domain.Usuario;
import com.feldmann.project.repositories.UsuarioRepository;
import com.feldmann.project.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario buscar(Long id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
					 "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));	
	}
	
	public Usuario inserir(Usuario obj) {
		obj.setId(null);
		
			return repo.save(obj);	
	}
	
}