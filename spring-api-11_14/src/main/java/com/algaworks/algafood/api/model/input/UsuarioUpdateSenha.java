package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateSenha {

  @NotBlank(message = "Senha atual é obrigatória")
  private String senhaAtual;
  
  @NotBlank(message = "Nova senha é obrigatória")
  private String novaSenha;
  
}
