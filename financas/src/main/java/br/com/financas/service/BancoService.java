package br.com.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.financas.model.BancoModel;
import br.com.financas.repository.BancoRepository;

@Service
public class BancoService {
	final BancoRepository bancoRepository;
	
	public BancoService(BancoRepository bancoRepository) {
		this.bancoRepository = bancoRepository;
	}
	
	@Transactional
	public BancoModel save(BancoModel bancoModel) {
		return bancoRepository.save(bancoModel);
	}
	
	public List<BancoModel> findAll() {
		return bancoRepository.findAll();
	}

	public Optional<BancoModel> findById(UUID id) {
		Optional<BancoModel> opt = bancoRepository.findById(id);
		return opt;
	}

	public void deleteById(UUID id) {
		bancoRepository.deleteById(id);
	}

	@Transactional
	public void delete(BancoModel bancoModel) {
		bancoRepository.delete(bancoModel);
		
	}
}
