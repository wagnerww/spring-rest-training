package com.algaworks.algafood.api.controller;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.model.input.FotoProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

  @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
      FotoProdutoInput fotoProdutoInput) {

    var nomeArquivo = fotoProdutoInput.getArquivo().getOriginalFilename();

    var arquivoFoto = Path.of("/Users/wagner.wagner/Documents/uploads_test", nomeArquivo);

    try {
      arquivo.transferTo(arquivoFoto);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}
