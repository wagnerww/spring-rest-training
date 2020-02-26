package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroCidadeService cadastroCidadeService;

  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    Long cozinhaId = restaurante.getCozinha().getId();

    Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
        .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));

    Cidade cidade = cadastroCidadeService.buscarOutFalhar(restaurante.getEndereco().getCidade().getId());

    restaurante.getEndereco().setCidade(cidade);
    restaurante.setCozinha(cozinha);
    return restauranteRepository.save(restaurante);
  }

  @Transactional
  public Restaurante buscarOuFalhar(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new RestauranteNaoEncontradaException(restauranteId));

  }

  @Transactional
  public void ativar(Long restauranteId) {
    Restaurante restaurante = this.buscarOuFalhar(restauranteId);

    restaurante.ativar();

  }

  @Transactional
  public void inativar(Long restauranteId) {
    Restaurante restaurante = this.buscarOuFalhar(restauranteId);

    restaurante.inativar();

  }

}
