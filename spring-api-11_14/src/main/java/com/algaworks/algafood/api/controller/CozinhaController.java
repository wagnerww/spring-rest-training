package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssmebler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinha;

  @Autowired
  private CozinhaModelAssmebler cozinhaModelAssmebler;

  @Autowired
  private CozinhaInputDisassembler cozinhaInputDisassembler;

  /* @ResponseStatus(value = HttpStatus.OK) */
  @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<List<CozinhaModel>> listar() {
    return new ResponseEntity<>(cozinhaModelAssmebler.toCollectionModel(cozinhaRepository.findAll()), HttpStatus.OK);
  }

  @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE })
  public CozinhasXmlWrapper listaXmlr() {
    return new CozinhasXmlWrapper(cozinhaRepository.findAll());
  }

  @GetMapping("/{cozinhaId}")
  public ResponseEntity<CozinhaModel> buscar(@PathVariable Long cozinhaId) {

    return new ResponseEntity<>(cozinhaModelAssmebler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId)),
        HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CozinhaModel> adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
    Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);

    return new ResponseEntity<>(cozinhaModelAssmebler.toModel(cadastroCozinha.salvar(cozinha)), HttpStatus.CREATED);
  }

  @PutMapping("/{cozinhaId}")
  public ResponseEntity<CozinhaModel> atualizar(@PathVariable Long cozinhaId,
      @RequestBody @Valid CozinhaInput cozinhaInput) {
    Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

    cozinhaInputDisassembler.copyDomainObject(cozinhaInput, cozinhaAtual);

    /* cozinhaAtual.setNome(cozinha.getNome()); */
    try {

      return new ResponseEntity<>(cozinhaModelAssmebler.toModel(cadastroCozinha.salvar(cozinhaAtual)), HttpStatus.OK);
    } catch (Exception e) {
      throw new NegocioException(e.getMessage());
    }

  }

  @DeleteMapping("/{cozinhaId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long cozinhaId) {
    cadastroCozinha.excluir(cozinhaId);
    return;
  }
}
