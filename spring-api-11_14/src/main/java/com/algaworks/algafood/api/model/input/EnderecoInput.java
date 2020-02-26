package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

  @NotBlank(message = "CEP é obrigatório")
  private String cep;

  @NotBlank(message = "Logradouro é obrigatório")
  private String logradouro;

  @NotBlank(message = "Número é obrigatório")
  private String numero;

  private String complemento;

  @NotBlank(message = "Bairro é obrigatório")
  private String bairro;

  @Valid
  @NotNull(message = "Cidade é obrigatória")
  private CidadeIdInput cidade;

}
