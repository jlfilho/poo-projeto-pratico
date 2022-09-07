package model.entities;

import java.time.LocalDateTime;

import model.util.ServicoDesconto;
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
		return "LocacaoLongoPeriodo [porcentagemDesconto=" + porcentagemDesconto + ", toString()=" + super.toString()
				+ "]";
	}

}
