package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	CozinhaRepository cozinhaReposity;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaReposity.save(cozinha);
	}
	
	@Transactional
	public void excluir(Long cozinhaId) {
		try {
			cozinhaReposity.deleteById(cozinhaId);
			//EXECUTA AS INSTRUÇÕES DO REPOSITÓRIO
			// isso foi feito, por que o @transactional está no método, e o commit
			// ou qunado a transação é encerrada, e aí é executado "commit"o problema
			// é que já pasou do try-catch.
			cozinhaReposity.flush();
		
		} catch(EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_USO, cozinhaId));
		}
	}
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaReposity.findById(cozinhaId)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
