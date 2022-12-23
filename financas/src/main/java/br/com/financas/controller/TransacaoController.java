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

import br.com.financas.dtos.TransacaoDto;
import br.com.financas.model.CategoriaModel;
import br.com.financas.model.TransacaoModel;
import br.com.financas.model.UsuarioModel;
import br.com.financas.service.CategoriaService;
import br.com.financas.service.TransacaoService;
import br.com.financas.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transacao")
public class TransacaoController {
	final TransacaoService transacaoService;
	final CategoriaService categoriaService;
	final UsuarioService usuarioService;
	
	public TransacaoController(TransacaoService transacaoService, CategoriaService categoriaService, UsuarioService usuarioService) {
		this.categoriaService = categoriaService;
		this.usuarioService = usuarioService;
		this.transacaoService = transacaoService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveTrasacao(@RequestBody @Valid TransacaoDto transacaoDto){
		var transacaoModel = new TransacaoModel();
		BeanUtils.copyProperties(transacaoDto, transacaoModel);
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(transacaoDto.getUsuario_id());
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		
		Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(transacaoDto.getCategoria_id());
		if(!categoriaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categria não encontrada");
		}
		transacaoModel.setUsuario(usuarioModelOptional.get());
		transacaoModel.setCategoria(categoriaModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.save(transacaoModel, transacaoDto.getConta_id(), false));

	}
		@GetMapping
		public ResponseEntity<List<TransacaoModel>> getAllTransacao(){
			return ResponseEntity.status(HttpStatus.FOUND).body(transacaoService.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Object> getOneTransacao(@PathVariable(value= "id") UUID id){
			Optional<TransacaoModel> transacaoModelOptional = transacaoService.findById(id);
			if(!transacaoModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacão não encontrada");
			}
			return ResponseEntity.status(HttpStatus.OK).body(transacaoModelOptional.get());
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Object> updateTransacao(@PathVariable(value= "id") UUID id, @RequestBody @Valid TransacaoDto transacaoDto) {
			Optional<TransacaoModel> transacaoModelOptional = transacaoService.findById(id);
			if(!transacaoModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada");
			}
			Optional<CategoriaModel> categoriaModelOptional = categoriaService.findById(transacaoDto.getCategoria_id());
			if(!categoriaModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada");
			}
			
			Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(transacaoDto.getUsuario_id());
			if(!usuarioModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
			}
			
			var transacaoModel = transacaoModelOptional.get();
			BeanUtils.copyProperties(transacaoDto, transacaoModel);
			
			return ResponseEntity.status(HttpStatus.OK).body(transacaoService.save(transacaoModel, transacaoDto.getConta_id(), true));
			
			
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Object> deleteTransacao(@PathVariable(value= "id") UUID id) {
			Optional<TransacaoModel> transacaoModelOptional = transacaoService.findById(id);
			if(!transacaoModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transação não encontrada");
			}
			transacaoService.delete(transacaoModelOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body("Transação excluida com sucesso");
		}
}
