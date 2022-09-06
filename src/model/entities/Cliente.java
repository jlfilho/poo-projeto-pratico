package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	
	private List<Telefone> telefones = new ArrayList<>();
	private List<Locacao> locacoes = new ArrayList<>();
	
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String nome, String cpf, String email) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public void addTelefone(Telefone telefone) {
		telefone.setCliente(this);
		telefones.add(telefone);
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public void addLocacao(Locacao locacao) {
		locacao.setCliente(this);
		locacoes.add(locacao);
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + "]";
	}
	
	
	

}
