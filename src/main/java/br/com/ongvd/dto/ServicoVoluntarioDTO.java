package br.com.ongvd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ServicoVoluntarioDTO {

	@NotBlank(message = "Este campo deve ser preenchido")
	private String nome;
	@NotBlank(message = "Este campo deve ser preenchido")
	@Size(min = 10, max = 5000, message = "Tamanho deve estar entre 10 e 5000")
	private String descricao;
	@NotNull
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

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
