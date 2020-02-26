package com.algaworks.algafood.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradaException(Long usuarioId) {
		this(String.format("Não existe cadastro de usuario com o id %d", usuarioId));
	}
	
}
