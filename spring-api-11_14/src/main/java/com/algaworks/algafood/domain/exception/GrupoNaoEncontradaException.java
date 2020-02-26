package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public GrupoNaoEncontradaException(Long grupoId) {
		this(String.format("Não existe cadastro de grupo com o id %d", grupoId));
	}
	
}
