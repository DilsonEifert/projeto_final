package br.com.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.financas.model.CategoriaModel;
import br.com.financas.repository.CategoriaRepository;


@Service
public class CategoriaService {
	
	final CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	@Transactional
	public CategoriaModel save(CategoriaModel categoriaModel) {
		return categoriaRepository.save(categoriaModel);
	}
	
	public List<CategoriaModel> findAll() {
		return categoriaRepository.findAll();
	}

	public Optional<CategoriaModel> findById(UUID id) {
		Optional<CategoriaModel> opt = categoriaRepository.findById(id);
		return opt;
	}

	public void deleteById(UUID id) {
		categoriaRepository.deleteById(id);
	}

	@Transactional
	public void delete(CategoriaModel categoriaModel) {
		categoriaRepository.delete(categoriaModel);
		
	}
}
