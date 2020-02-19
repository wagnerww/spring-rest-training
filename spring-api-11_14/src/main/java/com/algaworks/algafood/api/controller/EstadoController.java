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

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstado;

  @Autowired
  private EstadoModelAssembler estadoModelAssembler;

  @Autowired
  private EstadoInputDisassembler estadoInputDisassembler;

  @GetMapping
  public ResponseEntity<List<EstadoModel>> listar() {
    return new ResponseEntity<>(estadoModelAssembler.toCollectionModel(estadoRepository.findAll()), HttpStatus.OK);
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<EstadoModel> buscar(@PathVariable Long estadoId) {
    return new ResponseEntity<>(estadoModelAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId)), HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Estado adicionar(@RequestBody @Valid EstadoInput estadoInput) {
    Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

    return cadastroEstado.salvar(estado);
  }

  @PutMapping("/{estadoId}")
  public Estado atualizar(@PathVariable @Valid Long estadoId, @RequestBody EstadoInput estadoInput) {
    Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

    estadoInputDisassembler.copyDomainObject(estadoInput, estadoAtual);

    /* estadoAtual.setNome(estado.getNome()); */
    cadastroEstado.salvar(estadoAtual);
    return estadoAtual;
  }

  @DeleteMapping("/{estadoId}")
  public void remover(@PathVariable Long estadoId) {
    cadastroEstado.excluir(estadoId);
  }

}
