package com.feldmann.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;



@Entity
//faz o campo email e cpf serem unicos no nivel de tabela

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"})})
public class Usuario implements Serializable{
private static final long serialVersionUID = 1L;

/*O primeiro passo deve ser a construção de um cadastro de usuários,
 *  sendo obrigatório dados como: nome, e-mail, CPF e data de nascimento,
 *   onde e-mail e CPF devem ser únicos.*/

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotEmpty(message = "Preenchimento obrigatório")
@Size(min = 2, max = 50, message = "Nome de tamanho invalido nome deve conter entre 2 - 50 caracteres")
private String nome;

@NotEmpty(message = "Preenchimento obrigatório")
@Email(message = "Email Invalido")
@Column(unique = true)
private String email;

@NotEmpty(message = "Preenchimento obrigatório")
@CPF
private String cpf;

@NotEmpty(message = "Preenchimento obrigatório")
private String dataDeNascimento;

@OneToMany(fetch = FetchType.EAGER,mappedBy = "usuario")
private List<Endereco> enderecos = new ArrayList<>();

public Usuario() {
}


public Usuario(Long id,
		@NotEmpty(message = "Preenchimento obrigatório") @Size(min = 2, max = 50, message = "Nome de tamanho invalido nome deve conter entre 2 - 50 caracteres") String nome,
		@NotEmpty(message = "Preenchimento obrigatório") @Email(message = "Email Invalido") String email,
		@NotEmpty(message = "Preenchimento obrigatório") @CPF String cpf,
		@NotEmpty(message = "Preenchimento obrigatório") String dataDeNascimento) {
	super();
	this.id = id;
	this.nome = nome;
	this.email = email;
	this.cpf = cpf;
	this.dataDeNascimento = dataDeNascimento;
	
}




public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getCpf() {
	return cpf;
}

public void setCpf(String cpf) {
	this.cpf = cpf;
}

public String getDataDeNascimento() {
	return dataDeNascimento;
}

public void setDataDeNascimento(String dataDeNascimento) {
	this.dataDeNascimento = dataDeNascimento;
}



public List<Endereco> getEnderecos() {
	return enderecos;
}


public void setEnderecos(List<Endereco> enderecos) {
	this.enderecos = enderecos;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Usuario other = (Usuario) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}



}
