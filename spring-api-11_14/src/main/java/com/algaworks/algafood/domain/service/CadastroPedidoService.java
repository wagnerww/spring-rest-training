package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.PedidoNaoEncontradaException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;

@Service
public class CadastroPedidoService {

  @Autowired
  PedidoRepository pedidoRepository;

  public Page<Pedido> listar(PedidoFilter filtro, Pageable pageable) {
    return pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
  }

  public Pedido buscar(Long pedidoId) {
    return pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradaException(pedidoId));
  }

}
