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

import br.com.financas.dtos.ContaDto;
import br.com.financas.model.BancoModel;
import br.com.financas.model.ContaModel;
import br.com.financas.model.UsuarioModel;
import br.com.financas.service.BancoService;
import br.com.financas.service.ContaService;
import br.com.financas.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/conta")
public class ContaController {
	final ContaService contaService;
	final UsuarioService usuarioService;
	final BancoService bancoService;
	
	private static final Logger LOG = LogManager.getLogger("financas");
	
	public ContaController(ContaService contaService, BancoService bancoService, UsuarioService usuarioService) {
		this.bancoService = bancoService;
		this.usuarioService = usuarioService;
		this.contaService = contaService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveConta(@RequestBody @Valid ContaDto contaDto){
		var contaModel = new ContaModel();
		BeanUtils.copyProperties(contaDto, contaModel);
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(contaDto.getUsuario_id());
		if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		
		Optional<BancoModel> bancoModelOptional = bancoService.findById(contaDto.getBanco_id());
		if(!bancoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banco não encontrado");
		}
		
		contaModel.setUsuario(usuarioModelOptional.get());
		contaModel.setBanco(bancoModelOptional.get());
		
		LOG.info("Requesting save from Service");
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.save(contaModel));
	}
	
	@GetMapping
	public ResponseEntity<List<ContaModel>> getAllUsuario(){
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneConta(@PathVariable(value= "id") UUID id){
		Optional<ContaModel> contaModelOptional = contaService.findById(id);
		if(!contaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(contaModelOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateConta(@PathVariable(value= "id") UUID id, @RequestBody @Valid ContaDto contaDto) {
		Optional<ContaModel> contaModelOptional = contaService.findById(id);
		if(!contaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		
		var contaModel = contaModelOptional.get();
		BeanUtils.copyProperties(contaDto, contaModel);
		
		return ResponseEntity.status(HttpStatus.OK).body(contaService.save(contaModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConta(@PathVariable(value= "id") UUID id) {
		Optional<ContaModel> contaModelOptional = contaService.findById(id);
		if(!contaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		contaService.delete(contaModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso");
	}
}
