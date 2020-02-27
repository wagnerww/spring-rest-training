package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.Groups;
import com.algaworks.algafood.core.validation.TaxaFrete;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Nome é obrigatório")
  @Column(nullable = false)
  private String nome;

  // @DecimalMin("1")
  @TaxaFrete
  @Column(name = "taxa_frete", nullable = false)
  private BigDecimal taxaFrete;

  // Muitos restaurantes possuem uma cozinha
  @Valid
  @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
  @NotNull
  @ManyToOne()
  @JoinColumn(name = "cozinha_id", nullable = false)
  private Cozinha cozinha;

  @JsonIgnore
  @Embedded
  private Endereco endereco;

  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private OffsetDateTime dataCadastro;

  @UpdateTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private OffsetDateTime dataAtualizacao;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
  private Set<FormaPagamento> formasPagamento = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "restaurante")
  private Set<Produto> produtos = new HashSet<>();

  private Boolean ativo = Boolean.TRUE;

  public void ativar() {
    setAtivo(true);
  }

  public void inativar() {
    setAtivo(false);
  }
}
