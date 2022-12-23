package br.com.financas.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financas.dtos.CategoriaDto;
import br.com.financas.model.CategoriaModel;
import br.com.financas.model.UsuarioModel;
import br.com.financas.service.CategoriaService;
import br.com.financas.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categoria")
public class CategoriaController {
	
final CategoriaService categoriaService;
final UsuarioService usuarioService;
	
	public CategoriaController(CategoriaService categoriaService, UsuarioService usuarioService) {
		this.categoriaService = categoriaService;
		this.usuarioService = usuarioService;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Object> saveCategoria(@RequestBody @Valid CategoriaDto categoriaDto){
		var categoriaModel = new CategoriaModel();
		BeanUtils.copyProperties(categoriaDto, categoriaModel);
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(categoriaDto.getUsuario_id());
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		categoriaModel.setUsuario(usuarioModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaModel));

	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<CategoriaModel>> getAllCategoria(){
		return ResponseEntity.status(HttpStatus.FOUND).body(categoriaService.findAll());
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCategoria(@PathVariable(value= "id") UUID id){
		Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(id);
		if(!categoriaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(categoriaModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategoria(@PathVariable(value= "id") UUID id, @RequestBody @Valid CategoriaDto categoriaDto) {
		Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(id);
		if(!categoriaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
		}
		
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(categoriaDto.getUsuario_id());
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		
		var categoriaModel = categoriaModelOptional.get();
		categoriaModel.setNome(categoriaDto.getNome());
		//categoriaModel.setUsuario(categoriaDto.getUsuario());
		
		return ResponseEntity.status(HttpStatus.OK).body(categoriaService.save(categoriaModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCategoria(@PathVariable(value= "id") UUID id) {
		Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(id);
		if(!categoriaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
		}
		categoriaService.delete(categoriaModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Categoria excluida com sucesso");
	}
}
