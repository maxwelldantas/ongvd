package br.com.ongvd.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class EventoDTO {

	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	private Date dataInclusao;
	@NotEmpty
	private String orcamento;
	@NotEmpty
	private String contribuicaoParaEvento;
	@NotEmpty
	private String duracaoEvento;

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

	public String getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(String orcamento) {
		this.orcamento = orcamento;
	}

	public String getContribuicaoParaEvento() {
		return contribuicaoParaEvento;
	}

	public void setContribuicaoParaEvento(String contribuicaoParaEvento) {
		this.contribuicaoParaEvento = contribuicaoParaEvento;
	}

	public String getDuracaoEvento() {
		return duracaoEvento;
	}

	public void setDuracaoEvento(String duracaoEvento) {
		this.duracaoEvento = duracaoEvento;
	}

}
