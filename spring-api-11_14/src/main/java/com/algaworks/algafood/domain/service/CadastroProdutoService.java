package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

  @Autowired
  ProdutoRepository produtoRepository;

  public Produto salvar(Produto produto) {
    return produtoRepository.save(produto);
  }

  public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
    return produtoRepository.findById(restauranteId, produtoId)
        .orElseThrow(() -> new ProdutoNaoEncontradaException(restauranteId, produtoId));
  }

}
