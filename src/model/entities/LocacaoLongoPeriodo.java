package model.entities;

import java.time.LocalDateTime;

public class LocacaoLongoPeriodo extends Locacao {
	private Double porcentagemDesconto;
	
	public LocacaoLongoPeriodo() {
		
	}
	
	public LocacaoLongoPeriodo(Integer id, LocalDateTime dataRetirada, LocalDateTime dataDevolucao, Carro carro,
			Cliente cliente, Double porcentagemDesconto) {
		super(id, dataRetirada, dataDevolucao, carro, cliente);
		this.porcentagemDesconto = porcentagemDesconto;
	}



	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}
	

}
