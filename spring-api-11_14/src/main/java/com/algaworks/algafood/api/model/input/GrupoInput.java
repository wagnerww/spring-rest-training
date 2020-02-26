package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

  @NotBlank(message = "Nome do grupo é obrigatório")
  private String nome;

}
