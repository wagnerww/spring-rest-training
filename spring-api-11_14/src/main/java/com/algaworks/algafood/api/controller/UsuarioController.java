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

import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioUpdate;
import com.algaworks.algafood.api.model.input.UsuarioUpdateSenha;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  CadastroUsuarioService cadastroUsuarioService;

  @Autowired
  UsuarioModelAssembler usuarioModelAssembler;

  @Autowired
  UsuarioInputDisassembler usuarioInputDisassembler;

  @GetMapping
  public ResponseEntity<List<UsuarioModel>> listar() {
    return new ResponseEntity<>(usuarioModelAssembler.toCollectionModel(cadastroUsuarioService.listar()),
        HttpStatus.OK);
  }

  @GetMapping("/{usuarioId}")
  public ResponseEntity<UsuarioModel> buscar(@PathVariable Long usuarioId) {
    return new ResponseEntity<>(usuarioModelAssembler.toModel(cadastroUsuarioService.buscarOuFalhar(usuarioId)),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UsuarioModel> adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {

    Usuario novoUsuario = usuarioInputDisassembler.toDomainObject(usuarioInput);

    return new ResponseEntity<>(usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(novoUsuario)),
        HttpStatus.CREATED);

  }

  @PutMapping("/{usuarioId}")
  public ResponseEntity<UsuarioModel> editar(@PathVariable("usuarioId") Long usuarioId,
      @RequestBody @Valid UsuarioUpdate usuarioUpdate) {

    Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);

    usuarioInputDisassembler.copyDomainObject(usuarioUpdate, usuarioAtual);

    return new ResponseEntity<>(usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtual)),
        HttpStatus.OK);

  }

  @DeleteMapping("/{usuarioId}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long usuarioId) {
    cadastroUsuarioService.excluir(usuarioId);
  }

  @PutMapping("/{usuarioId}/senha")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioUpdateSenha senha) {
    cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
  }

}
