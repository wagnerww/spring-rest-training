package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
  
  @NotBlank(message = "Nome do produto é obrigatório")
  private String nome;

  @NotBlank(message = "Descrição do produto é obrigatório")
  private String descricao;

  @NotNull(message = "Preço do produto é obrigatório")
  @PositiveOrZero
  private BigDecimal preco;

  @NotNull(message = "Status do produto é obrigatório")
  private Boolean ativo;

}
