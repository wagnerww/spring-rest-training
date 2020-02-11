package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
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

		@ResponseStatus(value = HttpStatus.OK)
		@GetMapping(produces = 	{MediaType.APPLICATION_JSON_VALUE})
		public List<Cozinha> listar(){
			return cozinhaRepository.findAll();
		}
		
		@GetMapping(produces = 	{MediaType.APPLICATION_XML_VALUE})
		public CozinhasXmlWrapper listaXmlr(){
			return new CozinhasXmlWrapper(cozinhaRepository.findAll());
		}
		
		
		@GetMapping("/{cozinhaId}")
		public Cozinha buscar(@PathVariable Long cozinhaId) {
			return cadastroCozinha.buscarOuFalhar(cozinhaId);						
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
			return cadastroCozinha.salvar(cozinha);
		}
		
		@PutMapping("/{cozinhaId}")
		public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody  @Valid Cozinha cozinha){
			Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);			
			
			cozinhaAtual.setNome(cozinha.getNome());
			try {
				return cadastroCozinha.salvar(cozinhaAtual);
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
