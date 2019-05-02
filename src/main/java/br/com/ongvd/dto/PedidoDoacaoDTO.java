package br.com.ongvd.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class PedidoDoacaoDTO {

	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	private Date dataInclusao;
	@NotEmpty
	private String valorPedido;
	@NotEmpty
	private String itemPedido;

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

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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
}
