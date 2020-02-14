package com.algaworks.algafood;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class APICadastroCozinhaIT {

  @LocalServerPort
  private int port;

  @Autowired
  private DatabaseCleaner dataBaseCleaner;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  private static final int cozinhaIdInexistente = 100;

  private int quantidadeCozinhas = 0;
  private Cozinha cozinhaRussa = new Cozinha();
  private String jsonCorretoCozinhaChinesa;

  @Before
  public void setUp() {
    // Habilita o loggin caso falha, ele mostra o que teve como resposta
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;
    RestAssured.basePath = "/cozinhas";

    jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");

    dataBaseCleaner.clearTables();
    prepararDados();
  }

  @Test
  public void shouldRetornarStatus200_WhenCosultarCozinhas() {
    RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
  }

  @Test
  public void shouldContarQuantidadeCozinhas_WhenCosultarCozinhas() {
    RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(quantidadeCozinhas))
        .body("nome", Matchers.hasItems("Americana", "Russa"));
  }

  @Test
  public void shouldRetornar201_WhenCadastrarCozinha() {
    RestAssured.given().body(jsonCorretoCozinhaChinesa).contentType(ContentType.JSON).accept(ContentType.JSON).when()
        .post().then().statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void shouldRetornarRespostaEStatusCorretos_WhenConsultarCozinhaExistente() {
    RestAssured.given().pathParam("cozinhaId", cozinhaRussa.getId()).accept(ContentType.JSON).when().get("/{cozinhaId}")
        .then().statusCode(HttpStatus.OK.value()).body("nome", equalTo(cozinhaRussa.getNome()));
  }

  @Test
  public void shouldRetornarStatus404_WhenConsultarCozinhaInexistente() {
    RestAssured.given().pathParam("cozinhaId", cozinhaIdInexistente).accept(ContentType.JSON).when().get("/{cozinhaId}")
        .then().statusCode(HttpStatus.NOT_FOUND.value());
  }

  private void prepararDados() {
    Cozinha cozinha1 = new Cozinha();
    cozinha1.setNome("Americana");
    cozinhaRepository.save(cozinha1);

    cozinhaRussa.setNome("Russa");
    cozinhaRepository.save(cozinhaRussa);

    quantidadeCozinhas = (int) cozinhaRepository.count();
  }

}
