package br.com.ongvd.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String descricao;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp dataInclusao;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp dataAtualizacao;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp dataDesabilitado;
	private String orcamento;
	private String contribuicaoParaEvento;
	private String duracaoEvento;
	private Boolean habilitado;

	@ManyToOne
	@JoinColumn(name = "ong_id")
	private Ong ong;

	public Evento() {
	}

	public Evento(String nome, String descricao, Timestamp dataInclusao, Timestamp dataAtualizacao,
			Timestamp dataDesabilitado, String orcamento, String contribuicaoParaEvento, String duracaoEvento,
			Boolean habilitado, Ong ong) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
		this.dataAtualizacao = dataAtualizacao;
		this.dataDesabilitado = dataDesabilitado;
		this.orcamento = orcamento;
		this.contribuicaoParaEvento = contribuicaoParaEvento;
		this.duracaoEvento = duracaoEvento;
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataInclusao=" + dataInclusao
				+ ", dataAtualizacao=" + dataAtualizacao + ", dataDesabilitado=" + dataDesabilitado + ", orcamento="
				+ orcamento + ", contribuicaoParaEvento=" + contribuicaoParaEvento + ", duracaoEvento=" + duracaoEvento
				+ ", habilitado=" + habilitado + ", ong=" + ong + "]";
	}

}
