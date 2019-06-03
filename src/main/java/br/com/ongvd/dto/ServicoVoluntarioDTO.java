package br.com.ongvd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ServicoVoluntarioDTO {
	
	private Long id;
	@NotBlank(message = "Por favor preencha este campo")
	private String nome;
	@NotBlank(message = "Por favor preencha este campo")
	@Size(min = 10, max = 5000, message = "Tamanho do texto entre 10 a 5000 caracteres")
	private String descricao;
	private Boolean habilitado;
	
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

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
