package br.com.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.financas.model.ContaModel;
import br.com.financas.repository.ContaRepository;

@Service
public class ContaService {
	final ContaRepository contaRepository;
	
	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	@Transactional
	public ContaModel save(ContaModel contaModel) {
		return contaRepository.save(contaModel);
	}
	
	public List<ContaModel> findAll() {
		return contaRepository.findAll();
	}

	public Optional<ContaModel> findById(UUID id) {
		Optional<ContaModel> opt = contaRepository.findById(id);
		return opt;
	}

	public void deleteById(UUID id) {
		contaRepository.deleteById(id);
	}

	@Transactional
	public void delete(ContaModel contaModel) {
		contaRepository.delete(contaModel);
		
	}
}
