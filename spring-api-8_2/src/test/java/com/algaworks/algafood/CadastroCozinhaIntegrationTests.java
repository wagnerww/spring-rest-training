package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;

  @Test
  public void shouldDeveAtribuirId_WhenCadastrarCozinhaComDadosCorretos() {
    // Teste do tipo "happy path
    // cenário
    Cozinha novaCozinha = new Cozinha();
    novaCozinha.setNome("Chinesa");

    // ação
    novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

    // validação
    assertThat(novaCozinha).isNotNull();
    assertThat(novaCozinha.getId()).isNotNull();
    
  }
  
  @Test(expected = ConstraintViolationException.class)
  public void shouldFalhar_WhenCadastrarCozinhaSemNome() {
    Cozinha novaCozinha = new Cozinha();
    novaCozinha.setNome(null);
    
    novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
    
  }

}
