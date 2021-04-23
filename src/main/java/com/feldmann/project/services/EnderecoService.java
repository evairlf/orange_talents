package com.feldmann.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.feldmann.project.domain.Endereco;
import com.feldmann.project.repositories.EnderecoRepository;



@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	
	public Endereco inserir(Endereco obj) {
		obj.setId(null);
		
			return repo.save(obj);
		
	}
	
}
