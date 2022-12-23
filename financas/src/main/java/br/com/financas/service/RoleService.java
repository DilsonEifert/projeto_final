package br.com.financas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.financas.model.RoleModel;
import br.com.financas.repository.RoleRepository;

@Service
public class RoleService {
	final RoleRepository roleRepository;
	
	public RoleService (RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<RoleModel> findAll(){
		return roleRepository.findAll();
	}
	
	
}
