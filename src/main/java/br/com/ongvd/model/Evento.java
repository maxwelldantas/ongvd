package br.com.ongvd.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String descricao;
	private Date dataInclusao;
	private Date dataEncerramento;
	private String orcamento;
	private String contribuicaoParaEvento;
	private String duracaoEvento;
	private Boolean habilitado;
	
	@ManyToOne
	@JoinColumn(name = "ong_id")
	private Ong ong;

	public Evento() {
	}

	public Evento(String nome, String descricao, Date dataInclusao, Date dataEncerramento, String orcamento,
			String contribuicaoParaEvento, String duracaoEvento, Boolean habilitado) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
		this.dataEncerramento = dataEncerramento;
		this.orcamento = orcamento;
		this.contribuicaoParaEvento = contribuicaoParaEvento;
		this.duracaoEvento = duracaoEvento;
		this.habilitado = habilitado;
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

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
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
				+ ", dataEncerramento=" + dataEncerramento + ", orcamento=" + orcamento + ", contribuicaoParaEvento="
				+ contribuicaoParaEvento + ", duracaoEvento=" + duracaoEvento + ", habilitado=" + habilitado + "]";
	}

}
