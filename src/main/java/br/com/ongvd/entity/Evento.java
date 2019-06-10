package br.com.ongvd.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, length = 5000)
	private String descricao;
	@Column(nullable = false)
	private Timestamp dataInclusao;
	@Column
	private Timestamp dataAtualizacao;
	@Column
	private Timestamp dataDesabilitado;
	@Column(nullable = false)
	private String ingresso;
	@Column(nullable = false)
	private String horario;
	@Column
	private Boolean habilitado;
	@Column(nullable = false)
	private String cep;
	@Column(nullable = false)
	private String logradouro;
	@Column
	private Integer numero;
	@Column
	private String complemento;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String uf;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ong_id")
	private Ong ong;
	
	public Evento() {
	}

	public Evento(String nome, String descricao, Timestamp dataInclusao, Timestamp dataAtualizacao,
			Timestamp dataDesabilitado, String ingresso, String horario, Boolean habilitado, String cep,
			String logradouro, Integer numero, String complemento, String bairro, String cidade, String uf, Ong ong) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
		this.dataAtualizacao = dataAtualizacao;
		this.dataDesabilitado = dataDesabilitado;
		this.ingresso = ingresso;
		this.horario = horario;
		this.habilitado = habilitado;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
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

	public String getIngresso() {
		return ingresso;
	}

	public void setIngresso(String ingresso) {
		this.ingresso = ingresso;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
				+ ", dataAtualizacao=" + dataAtualizacao + ", dataDesabilitado=" + dataDesabilitado + ", ingresso="
				+ ingresso + ", horario=" + horario + ", habilitado=" + habilitado + ", cep=" + cep + ", logradouro="
				+ logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", uf=" + uf + ", ong=" + ong + "]";
	}

}
