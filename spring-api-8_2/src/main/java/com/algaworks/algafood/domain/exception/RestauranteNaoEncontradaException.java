package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradaException(Long restauranteId) {
		this(String.format("Não existe cadastro de resturante com o id %d", restauranteId));
	}
	
}
