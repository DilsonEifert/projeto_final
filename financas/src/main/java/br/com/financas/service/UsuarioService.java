package br.com.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.financas.model.UsuarioModel;
import br.com.financas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Transactional
	public UsuarioModel save(UsuarioModel usuarioModel) {
		return usuarioRepository.save(usuarioModel);
	}
	
	public List<UsuarioModel> findAll() {
		return usuarioRepository.findAll();
	}

	public Optional<UsuarioModel> findById(UUID id) {
		Optional<UsuarioModel> opt = usuarioRepository.findById(id);
		return opt;
	}

	public void deleteById(UUID id) {
		usuarioRepository.deleteById(id);
	}

	@Transactional
	public void delete(UsuarioModel usuarioModel) {
		usuarioRepository.delete(usuarioModel);
		
	}

}
