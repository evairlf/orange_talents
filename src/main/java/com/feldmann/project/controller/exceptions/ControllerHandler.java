package com.feldmann.project.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.feldmann.project.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerHandler {
	
	//Esse aqui pega o erro de object not found que está sendo usado no service pelo orElseThrow no qual ele retorna erro caso a requisição venha nula
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	//Esse aqui pega o erro de validação, no qual o cara tenta colocar algo no banco de dados que é obrigatorio como empty ou sem a validacao pedida
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"erro de Validação!",System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	
	//Esse aqui pega o DataIntegrityViolationException que da erro quando tenta inserir algo onde o valor é unico, tipo cpf
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> data(DataIntegrityViolationException e,HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de campo Unico",System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
}
