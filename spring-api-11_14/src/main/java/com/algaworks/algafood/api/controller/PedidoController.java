package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  CadastroPedidoService cadastroPedidoService;

  @Autowired
  PedidoModelAssembler pedidoModelAssembler;

  @GetMapping
  public ResponseEntity<List<PedidoModel>> listar() {
    return new ResponseEntity<>(pedidoModelAssembler.toCollectionModel(cadastroPedidoService.listar()), HttpStatus.OK);
  }

  @GetMapping("/{pedidoId}")
  public ResponseEntity<PedidoModel> buscar(@PathVariable("pedidoId") Long pedidoId) {
    return new ResponseEntity<>(pedidoModelAssembler.toModel(cadastroPedidoService.buscar(pedidoId)), HttpStatus.OK);
  }
}
