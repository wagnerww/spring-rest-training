package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
  private static final long serialVersionUID = 1L;

  public PedidoNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public PedidoNaoEncontradaException(Long pedidoId) {
    this(String.format("Não existe cadastro de pedido com o id %d", pedidoId));
  }

}
