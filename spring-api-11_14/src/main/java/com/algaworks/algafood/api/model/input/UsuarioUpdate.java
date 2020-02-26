package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdate {

  @NotBlank(message = "Nome é obrigatório")
  private String nome;

  @NotBlank(message = "Email é obrigatório")
  private String email;

}
