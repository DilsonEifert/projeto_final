package br.com.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.financas.model.TransacaoModel;
import br.com.financas.repository.TransacaoRepository;


@Service
public class TransacaoService {
	final TransacaoRepository transacaoRepository;
	
	public TransacaoService(TransacaoRepository transacaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}
	
	@Transactional
	public TransacaoModel save(TransacaoModel transacaoModel) {
		return transacaoRepository.save(transacaoModel);
	}
	
	public List<TransacaoModel> findAll() {
		return transacaoRepository.findAll();
	}

	public Optional<TransacaoModel> findById(UUID id) {
		Optional<TransacaoModel> opt = transacaoRepository.findById(id);
		return opt;
	}

	public void deleteById(UUID id) {
		transacaoRepository.deleteById(id);
	}

	@Transactional
	public void delete(TransacaoModel transacaoModel) {
		transacaoRepository.delete(transacaoModel);
		
	}
}
