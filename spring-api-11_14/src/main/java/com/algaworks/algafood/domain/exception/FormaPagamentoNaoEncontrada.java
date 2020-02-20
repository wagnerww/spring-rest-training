package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontrada extends EntidadeNaoEncontradaException {

  private static final long serialVersionUID = 1L;

  public FormaPagamentoNaoEncontrada(String mensagem) {
    super(mensagem);
  }

  public FormaPagamentoNaoEncontrada(Long formaPagamentoId) {
    this(String.format("Não existe cadastro de forma de pagamento com o id %d", formaPagamentoId));
  }

}
