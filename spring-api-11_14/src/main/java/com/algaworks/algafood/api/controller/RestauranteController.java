package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService cadastroRestaurante;

  @Autowired
  private RestauranteModelAssembler RestauranteModelAssembler;

  @Autowired
  private RestauranteInputDisassembler restauranteInputDisassembler;

  @ResponseStatus(value = HttpStatus.OK)

  @GetMapping
  public List<RestauranteModel> listar() {
    return RestauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
  }

  @GetMapping("/{restauranteId}")
  public RestauranteModel buscar(@PathVariable Long restauranteId) {

    Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

    return RestauranteModelAssembler.toModel(restaurante);
  }

  @PostMapping
  public ResponseEntity<RestauranteModel> adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
    try {
      Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

      return ResponseEntity.status(HttpStatus.CREATED)
          .body(RestauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante)));

    } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{restauranteId}")
  public ResponseEntity<RestauranteModel> atualizar(@PathVariable Long restauranteId,
      @RequestBody @Valid RestauranteInput restauranteInput) {

    // Restaurante restaurante =
    // restauranteInputDisassembler.toDomainObject(restauranteInput);

    Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

    restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

    /*
     * restauranteAtual.setNome(restaurante.getNome());
     * restauranteAtual.setCozinha(restaurante.getCozinha());
     */

    try {
      cadastroRestaurante.salvar(restauranteAtual);
      return ResponseEntity.ok(RestauranteModelAssembler.toModel(restauranteAtual));
    } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }

  } 

  private void merge(Map<String, Object> dadosOrigem, Restaurante camposDestino, HttpServletRequest request) {

    ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

      Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

      dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
        Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
        // habilita o acesso ao atributo/propriedade que esta privada
        field.setAccessible(true);

        Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

        System.out.println(nomePropriedade + " - " + valorPropriedade + " " + novoValor);

        ReflectionUtils.setField(field, camposDestino, novoValor);
      });
    } catch (IllegalArgumentException e) {
      Throwable rootCause = ExceptionUtils.getRootCause(e);
      throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
    }
  }

  @PutMapping("/{restauranteId}/ativar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativar(@PathVariable("restauranteId") Long restauranteId) {
    cadastroRestaurante.ativar(restauranteId);
  }

  @DeleteMapping("/{restauranteId}/inativar")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativar(@PathVariable("restauranteId") Long restauranteId) {
    cadastroRestaurante.inativar(restauranteId);
  }
  
  @PutMapping("/{restauranteId}/abertura")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void abrir(@PathVariable Long restauranteId) {
    cadastroRestaurante.abrir(restauranteId);
  }
  
  @PutMapping("/{restauranteId}/fechamento")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void fechar(@PathVariable Long restauranteId) {
    cadastroRestaurante.fechar(restauranteId);
  }
  
  @PutMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativarMultiplos(@RequestBody List<Long> restauranteId) {
    cadastroRestaurante.ativar(restauranteId);
  }
  
  @DeleteMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativarMultiplos(@RequestBody List<Long> restauranteId) {
    cadastroRestaurante.inativar(restauranteId);
  }

}
