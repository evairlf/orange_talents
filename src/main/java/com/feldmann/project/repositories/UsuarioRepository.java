package com.feldmann.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feldmann.project.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}	
