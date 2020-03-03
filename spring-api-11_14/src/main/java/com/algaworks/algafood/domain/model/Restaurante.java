package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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

  @ManyToMany
  @JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
  private Set<Usuario> responsaveis = new HashSet<>();

  private Boolean ativo = Boolean.TRUE;

  private Boolean aberto = Boolean.FALSE;

  public void ativar() {
    setAtivo(true);
  }

  public void inativar() {
    setAtivo(false);
  }

  public void abrir() {
    setAberto(true);
  }

  public void fechar() {
    setAberto(false);
  }

  public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
    return getFormasPagamento().contains(formaPagamento);
  }

  public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
    return !aceitaFormaPagamento(formaPagamento);
  }

  public boolean removerResponsavel(Usuario usuario) {
    return getResponsaveis().remove(usuario);
  }

  public boolean adicionarResponsavel(Usuario usuario) {
    return getResponsaveis().add(usuario);
  }

}
