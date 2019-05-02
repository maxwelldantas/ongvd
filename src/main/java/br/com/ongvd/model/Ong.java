package br.com.ongvd.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Ong {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String ramoAtividade;
	private String website;
	private String descricao;
	private String nomeContato;
	private String telefone;
	private String email;
	private String senha;
	private Boolean ativo;

	@OneToOne(mappedBy = "ong")
	private Endereco endereco;

	@OneToMany(mappedBy = "ong")
	private List<Evento> eventos;

	@OneToMany(mappedBy = "ong")
	private List<PedidoDoacao> doacoes;

	@OneToMany(mappedBy = "ong")
	private List<ServicoVoluntario> servicos;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public Ong() {
	}

	public Ong(String razaoSocial, String nomeFantasia, String cnpj, String ramoAtividade, String website,
			String descricao, String nomeContato, String telefone, String email, String senha, Boolean ativo,
			Endereco endereco, List<Evento> eventos, List<PedidoDoacao> doacoes, List<ServicoVoluntario> servicos,
			Collection<Role> roles) {
		super();
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.ramoAtividade = ramoAtividade;
		this.website = website;
		this.descricao = descricao;
		this.nomeContato = nomeContato;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
		this.endereco = endereco;
		this.eventos = eventos;
		this.doacoes = doacoes;
		this.servicos = servicos;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<PedidoDoacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<PedidoDoacao> doacoes) {
		this.doacoes = doacoes;
	}

	public List<ServicoVoluntario> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoVoluntario> servicos) {
		this.servicos = servicos;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ong other = (Ong) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ong [id=" + id + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj
				+ ", ramoAtividade=" + ramoAtividade + ", website=" + website + ", descricao=" + descricao
				+ ", nomeContato=" + nomeContato + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha
				+ ", ativo=" + ativo + ", endereco=" + endereco + ", eventos=" + eventos + ", doacoes=" + doacoes
				+ ", servicos=" + servicos + ", roles=" + roles + "]";
	}

}
