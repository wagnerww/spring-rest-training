package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.Groups;
import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
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

    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{restauranteId}")
  public ResponseEntity<RestauranteModel> atualizar(@PathVariable Long restauranteId,
      @RequestBody @Valid RestauranteInput restauranteInput) {

    Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

    Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

    restauranteAtual.setNome(restaurante.getNome());
    restauranteAtual.setCozinha(restaurante.getCozinha());

    try {
      cadastroRestaurante.salvar(restauranteAtual);
      return ResponseEntity.ok(RestauranteModelAssembler.toModel(restauranteAtual));
    } catch (CozinhaNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }

  }

  @PatchMapping("/{restauranteId}")
  public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos,
      HttpServletRequest request) {
    Optional<Restaurante> restauranteAtualObj = restauranteRepository.findById(restauranteId);

    if (!restauranteAtualObj.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Restaurante restauranteAtual = restauranteAtualObj.get();
    merge(campos, restauranteAtual, request);

    return this.atualizar(restauranteId, restauranteAtual);
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

}
