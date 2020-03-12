package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "usuario_cliente_id", nullable = false)
  private Usuario cliente;

  @Column(nullable = false)
  private BigDecimal subTotal;

  @Column(nullable = false)
  private BigDecimal taxaFrete;

  @Column(nullable = false)
  private BigDecimal valorTotal;

  @Column
  private OffsetDateTime dataCancelamento;

  @Column
  private OffsetDateTime dataEntrega;

  @Column
  private OffsetDateTime dataConfirmacao;

  @Column
  @Enumerated(EnumType.STRING)
  private StatusPedido status;

  @Embedded
  private Endereco enderecoEntrega;

  @ManyToOne
  @JoinColumn(name = "restaurante_id", nullable = false)
  private Restaurante restaurante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "forma_pagamento_id", nullable = false)
  private FormaPagamento formaPagamento;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<ItemPedido> itensPedido = new ArrayList<>();

  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private OffsetDateTime dataCadastro;

  @UpdateTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private OffsetDateTime dataAtualizacao;

  public void calcularValorTotal() {
    getItensPedido().forEach(ItemPedido::calcularPrecoTotal);

    this.subTotal = getItensPedido().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO,
        BigDecimal::add);

    this.valorTotal = this.subTotal.add(this.taxaFrete);
  }

}
