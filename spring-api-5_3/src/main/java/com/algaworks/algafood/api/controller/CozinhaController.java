package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
		public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
			Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
			// return ResponseEntity.status(HttpStatus.OK).body(cozinha);
			
			if(cozinha.isPresent()) {
				return ResponseEntity.ok(cozinha.get());
			}
			
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.notFound().build();
			
		}
		
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public Cozinha adicionar(@RequestBody Cozinha cozinha) {
			return cadastroCozinha.salvar(cozinha);
		}
		
		@PutMapping("/{cozinhaId}")
		public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
			Optional<Cozinha> cozinhaAtualObj = cozinhaRepository.findById(cozinhaId);
			if (cozinhaAtualObj.isPresent()) {
				Cozinha cozinhaAtual = cozinhaAtualObj.get();
				cozinhaAtual.setNome(cozinha.getNome());
				cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
				return ResponseEntity.ok(cozinhaAtual);
			}
			
			return ResponseEntity.notFound().build();
		}
		
		@DeleteMapping("/{cozinhaId}")
		public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
			
			try {
				cadastroCozinha.excluir(cozinhaId);			
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
				
			} catch(EntidadeNaoEncontradaException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}	
			
			
		}
}
