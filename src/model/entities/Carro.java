package model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.entities.enums.Cor;

public class Carro {
	private Integer id;
	private String modelo;
	private String placa;
	private Cor cor;
	private Integer ano;
	private LocalDate dataAquisicao;
	
	private Categoria categoria;
	private List<Locacao> locacoes = new ArrayList<>();
	
	public Carro() {
		
	}


	public Carro(Integer id, String modelo, String placa, Cor cor, Integer ano, LocalDate dataAquisicao,
			Categoria categoria) {
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;
		this.ano = ano;
		this.dataAquisicao = dataAquisicao;
		this.categoria = categoria;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void addLocacao(Locacao locacao) {
		locacao.setCarro(this);
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
		Carro other = (Carro) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Carro [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", cor=" + cor + ", ano=" + ano
				+ ", dataAquisicao=" + dataAquisicao + ", categoria=" + categoria.getDescricao() + "]";
	}
	
	
	
	
	
	
}
