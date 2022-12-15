package br.com.financas.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import br.com.financas.dtos.BancoDto;
import br.com.financas.model.BancoModel;
import br.com.financas.service.BancoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/banco")
public class BancoController {
	final BancoService bancoService;
	private static final Logger LOG = LogManager.getLogger("financas");
	
	public BancoController(BancoService bancoService) {
		this.bancoService = bancoService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveBanco(@RequestBody @Valid BancoDto bancoDto){
		var bancoModel = new BancoModel();
		BeanUtils.copyProperties(bancoDto, bancoModel);
		LOG.info("Requesting save from Service");
		return ResponseEntity.status(HttpStatus.CREATED).body(bancoService.save(bancoModel));
	}
	
	@GetMapping
	public ResponseEntity<List<BancoModel>> getAllUsuario(){
		return ResponseEntity.status(HttpStatus.CREATED).body(bancoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBanco(@PathVariable(value= "id") UUID id){
		Optional<BancoModel> bancoModelOptional = bancoService.findById(id);
		if(!bancoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(bancoModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBanco(@PathVariable(value= "id") UUID id, @RequestBody @Valid BancoDto bancoDto) {
		Optional<BancoModel> bancoModelOptional = bancoService.findById(id);
		if(!bancoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		
		var bancoModel = bancoModelOptional.get();
		BeanUtils.copyProperties(bancoDto, bancoModel);
		
		return ResponseEntity.status(HttpStatus.OK).body(bancoService.save(bancoModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBanco(@PathVariable(value= "id") UUID id) {
		Optional<BancoModel> bancoModelOptional = bancoService.findById(id);
		if(!bancoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		bancoService.delete(bancoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso");
	}
}
