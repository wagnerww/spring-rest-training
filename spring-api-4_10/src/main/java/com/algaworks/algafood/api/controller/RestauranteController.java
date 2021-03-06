package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.listar();
	}
	
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteRepository.buscar(restauranteId);
		
		if(restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
		try {
			Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
			
			if(restauranteAtual!=null) {
				restauranteAtual.setNome(restaurante.getNome());
				restauranteAtual.setCozinha(restaurante.getCozinha());
				
				cadastroRestaurante.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteAtual);
				
			}
			
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
		Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
		
		if(restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteAtual);
		
		return this.atualizar(restauranteId, restauranteAtual);
	}


	private void merge(Map<String, Object> dadosOrigem, Restaurante camposDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			// habilita o acesso ao atributo/propriedade que esta privada
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			System.out.println(nomePropriedade + " - " + valorPropriedade + " " + novoValor);
			
			ReflectionUtils.setField(field, camposDestino, novoValor);
		});
	}
	
}
