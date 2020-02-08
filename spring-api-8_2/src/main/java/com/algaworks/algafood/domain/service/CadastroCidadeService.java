package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois esta em uso";

	private static final String CIDADE_NAO_ENCONTRADA = "Cidade com o id %d não encontrada";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(CIDADE_NAO_ENCONTRADA, cidadeId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(CIDADE_EM_USO, cidadeId));
		}
	}
	
	public Cidade buscarOutFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
	
}
