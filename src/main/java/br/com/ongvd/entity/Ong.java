package br.com.ongvd.entity;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email", "cnpj" }))
public class Ong {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String razaoSocial;
	@Column(nullable = false)
	private String nomeFantasia;
	@Column(nullable = false)
	private String cnpj;
	@Column(nullable = false)
	private String areaDeAtuacao;
	@Column
	private String website;
	@Column(nullable = false)
	private String responsavel;
	@Column
	private Date fundacao;
	@Column(nullable = false)
	private String contato;
	private String whatsapp;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	@Column
	private Boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_enderecos", joinColumns = @JoinColumn(name = "ong_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "endereco_id", referencedColumnName = "id"))
	private List<Endereco> enderecos;

	@OneToMany(mappedBy = "ong", cascade = CascadeType.ALL)
	private Set<PedidoDoacao> doacoes;

	@OneToMany(mappedBy = "ong", cascade = CascadeType.ALL)
	private Set<ServicoVoluntario> servicos;
	
	@OneToMany(mappedBy = "ong", cascade = CascadeType.ALL)
	private Set<Evento> eventos;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "ong_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public Ong() {
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

	public String getAreaDeAtuacao() {
		return areaDeAtuacao;
	}

	public void setAreaDeAtuacao(String areaDeAtuacao) {
		this.areaDeAtuacao = areaDeAtuacao;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Date getFundacao() {
		return fundacao;
	}

	public void setFundacao(Date fundacao) {
		this.fundacao = fundacao;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<PedidoDoacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(Set<PedidoDoacao> doacoes) {
		this.doacoes = doacoes;
	}

	public Set<ServicoVoluntario> getServicos() {
		return servicos;
	}

	public void setServicos(Set<ServicoVoluntario> servicos) {
		this.servicos = servicos;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
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
				+ ", areaDeAtuacao=" + areaDeAtuacao + ", website=" + website + ", responsavel=" + responsavel
				+ ", fundacao=" + fundacao + ", contato=" + contato + ", whatsapp=" + whatsapp + ", telefone="
				+ telefone + ", email=" + email + ", senha=" + senha + ", ativo=" + ativo + "]";
	}

}
