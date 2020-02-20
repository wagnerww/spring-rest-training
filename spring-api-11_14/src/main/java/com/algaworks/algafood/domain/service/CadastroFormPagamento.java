package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontrada;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormPagamento {

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

}
