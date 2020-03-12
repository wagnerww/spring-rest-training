package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {

  @Autowired
  private FluxoPedidoService fluxoPedido;

  @PutMapping("/confirmacao")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void confirmar(@PathVariable("pedidoId") Long pedidoId) {
    fluxoPedido.confirmar(pedidoId);
  }

  @PutMapping("/cancelamento")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void cancelamento(@PathVariable("pedidoId") Long pedidoId) {
    fluxoPedido.cancelar(pedidoId);
  }

  @PutMapping("/entrega")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void entrega(@PathVariable("pedidoId") Long pedidoId) {
    fluxoPedido.entregar(pedidoId);
  }

}
