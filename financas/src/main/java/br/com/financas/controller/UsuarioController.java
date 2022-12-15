package br.com.financas.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financas.dtos.UsuarioDto;
import br.com.financas.model.UsuarioModel;
import br.com.financas.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {
	final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
		var usuarioModel = new UsuarioModel();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAllUsuario(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneUsuario(@PathVariable(value= "id") UUID id){
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUsuario(@PathVariable(value= "id") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		
		var usuarioModel = usuarioModelOptional.get();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value= "id") UUID id) {
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		usuarioService.delete(usuarioModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso");
	}
	

}
