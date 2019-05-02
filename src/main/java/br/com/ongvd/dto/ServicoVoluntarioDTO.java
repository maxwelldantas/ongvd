package br.com.ongvd.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class ServicoVoluntarioDTO {
	
	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	private Date dataInclusao;

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
}
