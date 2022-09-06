package model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Locacao {
	private Integer id;
	private LocalDateTime dataRetirada;
	private LocalDateTime dataDevolucao;
	
	private Carro carro;
	private Cliente cliente;
	
	
	public Locacao() {
		
	}


	public Locacao(Integer id, LocalDateTime dataRetirada, LocalDateTime dataDevolucao, Carro carro, Cliente cliente) {
		this.id = id;
		this.dataRetirada = dataRetirada;
		this.dataDevolucao = dataDevolucao;
		this.carro = carro;
		this.cliente = cliente;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDateTime getDataRetirada() {
		return dataRetirada;
	}


	public void setDataRetirada(LocalDateTime dataRetirada) {
		this.dataRetirada = dataRetirada;
	}


	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}


	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public Carro getCarro() {
		return carro;
	}


	public void setCarro(Carro carro) {
		this.carro = carro;
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
		Locacao other = (Locacao) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Locacao [id=" + id + ", dataRetirada=" + dataRetirada + ", dataDevolucao=" + dataDevolucao + ", carro="
				+ carro + ", cliente=" + cliente + "]";
	}
	
	

}
