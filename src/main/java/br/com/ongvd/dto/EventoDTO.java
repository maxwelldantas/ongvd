package br.com.ongvd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EventoDTO {

	@NotBlank(message = "Por favor preencha este campo")
	private String nome;
	@NotBlank(message = "Por favor preencha este campo")
	@Size(min = 10, max = 5000, message = "Tamanho do texto entre 10 a 5000 caracteres")
	private String descricao;
	@NotBlank(message = "Por favor preencha este campo")
	private String orcamento;
	@NotBlank(message = "Por favor preencha este campo")
	private String contribuicaoParaEvento;
	@NotBlank(message = "Por favor preencha este campo")
	private String duracaoEvento;
	private Boolean habilitado;

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

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
