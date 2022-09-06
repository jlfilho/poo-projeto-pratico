package model.entities;

import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDateTime;

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
		return (long) Math.ceil(Duration.between(super.getDataRetirada(),super.getDataDevolucao()).toMinutes()/(1440.0+60.0));
	}

	@Override
	public String toString() {
		return "LocacaoDiaria [diasPrevistoDevolucao=" + diasPrevistoDevolucao + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	

}
