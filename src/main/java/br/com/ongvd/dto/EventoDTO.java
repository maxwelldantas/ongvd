package br.com.ongvd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EventoDTO {

	private Long id;
	@NotBlank(message = "Por favor preencha este campo")
	private String nome;
	@Size(min = 10, max = 5000, message = "Tamanho do texto entre 10 a 5000 caracteres")
	private String descricao;
	@NotBlank(message = "Por favor preencha este campo")
	private String ingresso;
	@NotBlank(message = "Por favor preencha este campo")
	private String horario;
	private Boolean habilitado;
	@NotBlank(message = "Por favor preencha este campo")
	private String cep;
	@NotBlank(message = "Por favor preencha este campo")
	private String logradouro;
	private Integer numero;
	private String complemento;
	@NotBlank(message = "Por favor preencha este campo")
	private String bairro;
	@NotBlank(message = "Por favor preencha este campo")
	private String cidade;
	@NotBlank(message = "Por favor preencha este campo")
	private String uf;

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
}
