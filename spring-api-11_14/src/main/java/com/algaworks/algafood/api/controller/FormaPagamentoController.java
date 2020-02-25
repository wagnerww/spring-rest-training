package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.FormaPagamentoDisassembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.service.CadastroFormaPagamento;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

  @Autowired
  private CadastroFormaPagamento cadastroFormaPagamento;

  @Autowired
  private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

  @Autowired
  private FormaPagamentoDisassembler formaPagamentoDisassembler;

  @GetMapping
  public ResponseEntity<List<FormaPagamentoModel>> listar() {

    return new ResponseEntity<>(formaPagamentoModelAssembler.toCollectionModel(cadastroFormaPagamento.buscarTodos()),
        HttpStatus.OK);
  }

  @GetMapping("/{formaPagamentoId}")
  public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
    return new ResponseEntity<>(
        formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.buscarForma(formaPagamentoId)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<FormaPagamentoModel> adicionar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
    FormaPagamento formaPagamento = formaPagamentoDisassembler.toDomainObject(formaPagamentoInput);
    return new ResponseEntity<>(formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamento)),
        HttpStatus.CREATED);
  }

  @PutMapping("/{formaPagamentoId}")
  public ResponseEntity<FormaPagamentoModel> editar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput,
      @PathVariable("formaPagamentoId") Long formaPagamentoId) {

    FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarForma(formaPagamentoId);
    formaPagamentoDisassembler.copyDomainObject(formaPagamentoInput, formaPagamentoAtual);

    try {
      return new ResponseEntity<>(
          formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamentoAtual)), HttpStatus.OK);
    } catch (Exception e) {
      throw new NegocioException(e.getMessage());
    }
  }

  @DeleteMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
    cadastroFormaPagamento.excluir(formaPagamentoId);
  }

}
