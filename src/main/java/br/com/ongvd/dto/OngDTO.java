package br.com.ongvd.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.ongvd.constraint.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first = "senha", second = "confirmarSenha", message = "Os campos de senha devem corresponder"),
	@FieldMatch(first = "email", second = "confirmarEmail", message = "Os campos de e-mail devem corresponder") })
public class OngDTO {
	
	@NotEmpty
	private String razaoSocial;
	@NotEmpty
	private String nomeFantasia;
	@NotEmpty
	private String cnpj;
	@NotEmpty
	private String ramoAtividade;
	@NotEmpty
	private String website;
	@NotEmpty
	private String descricao;
	@NotEmpty
	private String nomeContato;
	@NotEmpty
	private String telefone;
	@Email
	@NotEmpty
	private String email;
	@Email
	@NotEmpty
	private String confirmarEmail;
	@NotEmpty
	private String senha;
	@NotEmpty
	private String confirmarSenha;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmarEmail() {
		return confirmarEmail;
	}

	public void setConfirmarEmail(String confirmarEmail) {
		this.confirmarEmail = confirmarEmail;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}
