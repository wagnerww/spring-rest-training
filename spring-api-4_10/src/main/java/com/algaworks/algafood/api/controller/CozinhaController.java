package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;


@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

		@ResponseStatus(value = HttpStatus.OK)
		@GetMapping(produces = 	{MediaType.APPLICATION_JSON_VALUE})
		public List<Cozinha> listar(){
			return cozinhaRepository.listar();
		}
		
		@GetMapping(produces = 	{MediaType.APPLICATION_XML_VALUE})
		public CozinhasXmlWrapper listaXmlr(){
			return new CozinhasXmlWrapper(cozinhaRepository.listar());
		}
		
		
		@GetMapping("/{cozinhaId}")
		public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
			Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
			// return ResponseEntity.status(HttpStatus.OK).body(cozinha);
			
			if(cozinha != null) {
				return ResponseEntity.ok(cozinha);
			}
			
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.notFound().build();
			
		}
}
