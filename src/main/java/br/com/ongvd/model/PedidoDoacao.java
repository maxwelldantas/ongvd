package br.com.ongvd.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoDoacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, length = 5000)
	private String descricao;
	@Column(nullable = false)
	private Timestamp dataInclusao;
	private Timestamp dataAtualizacao;
	private Timestamp dataDesabilitado;
	@Column(nullable = false)
	private String valorPedido;
	@Column(nullable = false)
	private String itemPedido;
	private Boolean habilitado;
	
	@ManyToOne
	@JoinColumn(name = "ong_id")
	private Ong ong;

	public PedidoDoacao() {
	}

	public PedidoDoacao(String nome, String descricao, Timestamp dataInclusao, Timestamp dataAtualizacao,
			Timestamp dataDesabilitado, String valorPedido, String itemPedido, Boolean habilitado, Ong ong) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
		this.dataAtualizacao = dataAtualizacao;
		this.dataDesabilitado = dataDesabilitado;
		this.valorPedido = valorPedido;
		this.itemPedido = itemPedido;
		this.habilitado = habilitado;
		this.ong = ong;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Timestamp dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Timestamp getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Timestamp dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Timestamp getDataDesabilitado() {
		return dataDesabilitado;
	}

	public void setDataDesabilitado(Timestamp dataDesabilitado) {
		this.dataDesabilitado = dataDesabilitado;
	}

	public String getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(String valorPedido) {
		this.valorPedido = valorPedido;
	}

	public String getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(String itemPedido) {
		this.itemPedido = itemPedido;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Ong getOng() {
		return ong;
	}

	public void setOng(Ong ong) {
		this.ong = ong;
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
		PedidoDoacao other = (PedidoDoacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoDoacao [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataInclusao="
				+ dataInclusao + ", dataAtualizacao=" + dataAtualizacao + ", dataDesabilitado=" + dataDesabilitado
				+ ", valorPedido=" + valorPedido + ", itemPedido=" + itemPedido + ", habilitado=" + habilitado
				+ ", ong=" + ong + "]";
	}

}
