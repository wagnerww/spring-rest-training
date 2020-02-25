package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontrada;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamento {

  private static final String FORMA_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

  @Autowired
  private FormaPagamentoRepository formaPagamentoRepository;

  public List<FormaPagamento> buscarTodos() {
    return formaPagamentoRepository.findAll();
  }

  public FormaPagamento buscarForma(Long formaPagamentoId) {
    return formaPagamentoRepository.findById(formaPagamentoId)
        .orElseThrow(() -> new FormaPagamentoNaoEncontrada(formaPagamentoId));
  }

  public FormaPagamento salvar(FormaPagamento formaPagamento) {
    return formaPagamentoRepository.save(formaPagamento);
  }

  public void excluir(Long formaPagamentoId) {
    try {

      formaPagamentoRepository.deleteById(formaPagamentoId);

    } catch (EmptyResultDataAccessException e) {
      throw new FormaPagamentoNaoEncontrada(formaPagamentoId);

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(String.format(FORMA_EM_USO, formaPagamentoId));

    }
  }

}
