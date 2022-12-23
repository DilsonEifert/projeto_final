package br.com.financas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financas.model.RoleModel;
import br.com.financas.service.RoleService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/role")
public class RoleController {

	final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public ResponseEntity<List<RoleModel>> getAllRole(){
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.findAll());
	}
	
}
