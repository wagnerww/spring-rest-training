package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algafood.domain.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoModel {

  private Long id;
  private Usuario cliente;
  private BigDecimal subTotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;
  private OffsetDateTime dataCriacao;
  private String status;
  private RestauranteResumoModel restaurante;

}
