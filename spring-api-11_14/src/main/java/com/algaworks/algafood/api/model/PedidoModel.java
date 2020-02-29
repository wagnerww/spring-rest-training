package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

  private Long id;
  private Usuario cliente;
  private BigDecimal subTotal;
  private BigDecimal taxaFrete;
  private BigDecimal valorTotal;
  private OffsetDateTime dataCriacao;
  private OffsetDateTime dataCancelamento;
  private OffsetDateTime dataEntrega;
  private String status;
  private EnderecoModel enderecoEntrega;
  private RestauranteResumoModel restaurante;
  private FormaPagamentoModel formaPagamento;
  private List<ItemPedidoModel> itensPedido = new ArrayList<>();
  private OffsetDateTime dataCadastro;
  private OffsetDateTime dataAtualizacao;
  
}
