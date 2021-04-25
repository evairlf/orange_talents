package com.feldmann.project.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.feldmann.project.domain.feign.EnderecoVia;
import com.feldmann.project.repositories.EnderecoViacep;


@Service
public class ViacepService {

	@Autowired
	EnderecoViacep enderecoViacep;
	
	public EnderecoVia buscarCep(String cep) {
		EnderecoVia obj = enderecoViacep.buscaEnderecoPor(cep);
		return obj;
	}
	
}
