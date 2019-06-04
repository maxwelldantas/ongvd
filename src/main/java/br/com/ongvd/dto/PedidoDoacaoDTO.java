package br.com.ongvd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PedidoDoacaoDTO {

	private Long id;
	@NotBlank(message = "Por favor preencha este campo")
	private String nome;
	@Size(min = 10, max = 5000, message = "Tamanho do texto entre 10 a 5000 caracteres")
	private String descricao;
	private String valorPedido;
	@NotBlank(message = "Por favor preencha este campo")
	private String itemPedido;
	private Boolean habilitado;

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
}
