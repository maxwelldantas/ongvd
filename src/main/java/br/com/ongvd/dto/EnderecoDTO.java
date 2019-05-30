package br.com.ongvd.dto;

import javax.validation.constraints.NotBlank;

public class EnderecoDTO {

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
