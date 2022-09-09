package model.entities;

import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDateTime;

import model.util.TipoLocacao;

public class LocacaoDiaria extends Locacao {
	private Long diasPrevistoDevolucao;
	
	public LocacaoDiaria() {
		
	}

	public LocacaoDiaria(Integer id, LocalDateTime dataRetirada, LocalDateTime dataDevolucao, Carro carro,
			Cliente cliente) {
		super(id, dataRetirada, dataDevolucao, carro, cliente);
		this.diasPrevistoDevolucao =  diasPrevistoDevolucao();
	}

	public Long getDiasPrevistoDevolucao() {
		return diasPrevistoDevolucao;
	}

	public void setDiasPrevistoDevolucao(Long diasPrevistoDevolucao) {
		this.diasPrevistoDevolucao = diasPrevistoDevolucao;
	}

	private long diasPrevistoDevolucao() {
		return TipoLocacao.periodoEmDias(getDataRetirada(), getDataDevolucao());
	}

	@Override
	public String toString() {
		return "Locação Diária: \ndias Previsto Devolução: " + diasPrevistoDevolucao + ", \n" + super.toString();
	}
	
	
	

}
