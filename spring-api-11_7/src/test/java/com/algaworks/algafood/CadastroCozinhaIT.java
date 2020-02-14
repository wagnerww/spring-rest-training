package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIT {

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;

  @Autowired
  private CozinhaRepository cozinhaRepository;

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

  @Test(expected = EntidadeEmUsoException.class)
  public void shouldFalhar_WhenExcluirCozinhaEmUso() {
    cadastroCozinhaService.excluir(2L);
  }

  @Test(expected = CozinhaNaoEncontradaException.class)
  public void shouldFalhar_WhenExcluirCozinhaInexistente() {
    cadastroCozinhaService.excluir(100L);
  }
  
}
