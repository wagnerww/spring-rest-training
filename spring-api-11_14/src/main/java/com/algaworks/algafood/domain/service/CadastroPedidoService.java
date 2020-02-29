package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.PedidoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

  @Autowired
  PedidoRepository pedidoRepository;

  public List<Pedido> listar() {
    return pedidoRepository.findAllCustom();
  }

  public Pedido buscar(Long pedidoId) {
    return pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradaException(pedidoId));
  }

}
