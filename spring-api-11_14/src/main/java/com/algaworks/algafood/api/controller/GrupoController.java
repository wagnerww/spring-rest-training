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

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

  @Autowired
  CadastroGrupoService cadastroCadastroGrupoService;

  @Autowired
  GrupoInputDisassembler grupoInputDisasembler;

  @Autowired
  GrupoModelAssembler grupoModelAssembler;

  @GetMapping
  public ResponseEntity<List<GrupoModel>> listar() {
    return new ResponseEntity<>(grupoModelAssembler.toCollectionModel(cadastroCadastroGrupoService.listar()),
        HttpStatus.OK);
  }

  @GetMapping("/{grupoId}")
  public ResponseEntity<GrupoModel> buscar(@PathVariable Long grupoId) {
    return new ResponseEntity<>(grupoModelAssembler.toModel(cadastroCadastroGrupoService.buscarOutFalhar(grupoId)),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<GrupoModel> adicionar(@RequestBody @Valid GrupoInput grupoInput) {

    Grupo novoGrupo = grupoInputDisasembler.toDomainObject(grupoInput);

    return new ResponseEntity<>(grupoModelAssembler.toModel(cadastroCadastroGrupoService.salvar(novoGrupo)),
        HttpStatus.CREATED);
  }

  @PutMapping("/{grupoId}")
  public ResponseEntity<GrupoModel> editar(@PathVariable("grupoId") Long grupoId,
      @RequestBody @Valid GrupoInput grupoInput) {

    Grupo grupoAtual = cadastroCadastroGrupoService.buscarOutFalhar(grupoId);

    grupoInputDisasembler.copyDomainObject(grupoInput, grupoAtual);

    return new ResponseEntity<>(grupoModelAssembler.toModel(cadastroCadastroGrupoService.salvar(grupoAtual)),
        HttpStatus.OK);
  }

  @DeleteMapping("/{grupoId}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void remover(@PathVariable("grupoId") Long grupoId) {
    cadastroCadastroGrupoService.excluir(grupoId);
  }

}
