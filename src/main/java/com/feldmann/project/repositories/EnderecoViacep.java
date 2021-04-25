package com.feldmann.project.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.feldmann.project.domain.feign.EnderecoVia;


@FeignClient(url="https://viacep.com.br/ws/", name = "viacep")
public interface EnderecoViacep {
	
	@GetMapping("{cep}/json")
	EnderecoVia buscaEnderecoPor(@PathVariable("cep") String cep);
	
}
