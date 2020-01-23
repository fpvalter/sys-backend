package com.sysbackend.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "pedido_item")
public class PedidoItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private PedidoItemPK id = new PedidoItemPK();
	
	private Double desconto;
	private Double quantidade;
	private Double preco;
	
	public PedidoItem() {
		
	}
	
	public PedidoItem(Pedido pedido, Produto produto, Double desconto, Double quantidade, Double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public PedidoItemPK getId() {
		return id;
	}

	public void setId(PedidoItemPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}
		
	public Produto getProduto() {
		return this.id.getProduto();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoItem other = (PedidoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
