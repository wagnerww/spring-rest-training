package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDisasssembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroPedidoService;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  CadastroPedidoService cadastroPedidoService;

  @Autowired
  PedidoModelAssembler pedidoModelAssembler;

  @Autowired
  PedidoResumoModelAssembler pedidoResumoModelAssembler;

  @Autowired
  private PedidoInputDisasssembler pedidoInputDisassembler;

  @Autowired
  EmissaoPedidoService emissaoPedido;

  @GetMapping
  public ResponseEntity<List<PedidoResumoModel>> listar() {
    return new ResponseEntity<>(pedidoResumoModelAssembler.toCollectionModel(cadastroPedidoService.listar()),
        HttpStatus.OK);
  }

  @GetMapping("/{pedidoId}")
  public ResponseEntity<PedidoModel> buscar(@PathVariable("pedidoId") Long pedidoId) {
    return new ResponseEntity<>(pedidoModelAssembler.toModel(cadastroPedidoService.buscar(pedidoId)), HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
    try {
      Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

      // TODO pegar usuário autenticado
      novoPedido.setCliente(new Usuario());
      novoPedido.getCliente().setId(1L);

      novoPedido = emissaoPedido.emitir(novoPedido);

      return pedidoModelAssembler.toModel(novoPedido);
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }
}
