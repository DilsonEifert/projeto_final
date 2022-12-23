package br.com.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.financas.model.ContaModel;
import br.com.financas.model.TransacaoModel;
import br.com.financas.repository.ContaRepository;
import br.com.financas.repository.TransacaoRepository;


@Service
public class TransacaoService {
	final TransacaoRepository transacaoRepository;
	final ContaRepository contaRepository;
	
	public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
		this.transacaoRepository = transacaoRepository;
		this.contaRepository = contaRepository;
	}
	
	@Transactional
	public TransacaoModel save(TransacaoModel transacaoModel, UUID id, boolean update) {
		if(!update) {
			Optional<ContaModel> contaModelOpt = contaRepository.findById(id);
			ContaModel contaModel = contaModelOpt.get();
			if(transacaoModel.getCredito() == 'c') {
				contaModel.setValor(contaModel.getValor() + transacaoModel.getValor());
			} else {
				contaModel.setValor(contaModel.getValor() - transacaoModel.getValor());
			}
			contaRepository.save(contaModel);
		}
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
