package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Categoria {
	private Integer id;
	private String descricao;
	private Double precoDiaria;
	
	private List<Carro> carros = new ArrayList<>();
	
	
	public Categoria() {
		
	}


	public Categoria(Integer id, String descricao, Double precoDiaria) {
		this.id = id;
		this.descricao = descricao;
		this.precoDiaria = precoDiaria;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Double getPrecoDiaria() {
		return precoDiaria;
	}


	public void setPrecoDiaria(Double precoDiaria) {
		this.precoDiaria = precoDiaria;
	}
	
	public void addLocacao(Carro carro) {
		carro.setCategoria(this);
		carros.add(carro);
	}


	public List<Carro> getCarros() {
		return carros;
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
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
	

}
