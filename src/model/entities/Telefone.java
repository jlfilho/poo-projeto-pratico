package model.entities;

import java.util.Objects;

public class Telefone {
	 private Integer id;
	
	private String numero;
	
	private Cliente cliente;
	
	public Telefone() {
		
	}


	public Telefone(Integer id, String numero, Cliente cliente) {
		this.numero = numero;
		this.cliente = cliente;
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Telefone: numero: " + numero;
	}

}
