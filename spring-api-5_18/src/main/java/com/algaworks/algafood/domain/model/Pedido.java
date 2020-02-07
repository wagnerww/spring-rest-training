package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.catalina.User;
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
	
	@Column(nullable = false)
	private Date dataCriacao;
		
	@Column
	private Date dataCancelamento;
	
	@Column
	private Date dataEntrega;
	
	@Column
	private StatusPedido status;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<ItemPedido> itensPedido = new ArrayList<>();
	
	@CreationTimestamp()
	@Column(nullable = false, columnDefinition = "datetime")
	private Date dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private Date dataAtualizacao;

		
}
