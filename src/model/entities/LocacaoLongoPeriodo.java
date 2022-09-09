package model.entities;

import java.time.LocalDateTime;

import model.service.ServicoDesconto;
import model.util.TipoLocacao;

public class LocacaoLongoPeriodo extends Locacao {
	private Double porcentagemDesconto;
	
	public LocacaoLongoPeriodo() {
		
	}
	
	public LocacaoLongoPeriodo(Integer id, LocalDateTime dataRetirada, LocalDateTime dataDevolucao, Carro carro,
			Cliente cliente) {
		super(id, dataRetirada, dataDevolucao, carro, cliente);
		this.porcentagemDesconto = ServicoDesconto.porcentagemDesconto(dataRetirada, dataDevolucao);
	}

	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	@Override
	public String toString() {
		return "Locação Longo Período: \nDesconto: " + porcentagemDesconto + ", \n" + super.toString();
	}

}
