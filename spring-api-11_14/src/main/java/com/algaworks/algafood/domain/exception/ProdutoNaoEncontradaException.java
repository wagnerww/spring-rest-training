package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {
  private static final long serialVersionUID = 1L;

  public ProdutoNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public ProdutoNaoEncontradaException(Long produtoId, Long restauranteId) {
    this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d", produtoId,
        restauranteId));
  }

}
