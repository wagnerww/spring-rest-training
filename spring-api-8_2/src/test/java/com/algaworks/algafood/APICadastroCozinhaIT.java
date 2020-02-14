package com.algaworks.algafood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APICadastroCozinhaIT {
  
  @LocalServerPort
  private int port;

  @Test
  public void shouldRetornarStatus200_WhenCosultarCozinhas() {
    // Habilita o loggin caso falha, ele mostra o que teve como resposta
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    
    RestAssured.given().basePath("/cozinhas")
    .port(port)
    .accept(ContentType.JSON)
    .when()
    .get()
    .then()
    .statusCode(HttpStatus.OK.value());
  }

}
