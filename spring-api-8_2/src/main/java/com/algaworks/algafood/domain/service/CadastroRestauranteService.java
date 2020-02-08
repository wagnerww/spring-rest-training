package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_NAO_EXISTE = "Não existe cadastro de restaurante com o id %d";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com o código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_RESTAURANTE_NAO_EXISTE, restauranteId)));

	}
	
}
