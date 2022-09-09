package model.service;

import java.time.LocalDateTime;

import model.entities.Locacao;
import model.entities.LocacaoLongoPeriodo;
import model.util.TipoLocacao;

public class ServicoDesconto {
	private static final int DESCONTO1 = 30;
	private static final int DESCONTO2 = 30 * 3;
	private static final int DESCONTO3 = 30 * 6;

	public static double porcentagemDesconto(LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {
		if (TipoLocacao.isLocacaoLongoPeriodo(dataRetirada, dataDevolucao)) {
			if (TipoLocacao.periodoEmDias(dataRetirada, dataDevolucao) <= DESCONTO1) {
				return 0.05;
			}
			if (TipoLocacao.periodoEmDias(dataRetirada, dataDevolucao) <= DESCONTO2) {
				return 0.10;
			}
			if (TipoLocacao.periodoEmDias(dataRetirada, dataDevolucao) <= DESCONTO3) {
				return 0.20;
			} else {
				return 0.30;
			}
		} else {
			return 0.0;
		}
	}
	
	public static double porcentagemDesconto(Locacao loc) {
		if (TipoLocacao.isLocacaoLongoPeriodo(loc.getDataRetirada(), loc.getDataDevolucao())) {
			LocacaoLongoPeriodo locacao = (LocacaoLongoPeriodo) loc;
			if (TipoLocacao.periodoEmDias(locacao.getDataRetirada(), locacao.getDataDevolucao()) <= DESCONTO1) {
				locacao.setPorcentagemDesconto(0.05);
				return locacao.getPorcentagemDesconto();
			}
			if (TipoLocacao.periodoEmDias(locacao.getDataRetirada(), locacao.getDataDevolucao()) <= DESCONTO2) {
				locacao.setPorcentagemDesconto(0.10);
				return locacao.getPorcentagemDesconto();
			}
			if (TipoLocacao.periodoEmDias(locacao.getDataRetirada(), locacao.getDataDevolucao()) <= DESCONTO3) {
				locacao.setPorcentagemDesconto(0.20);
				return locacao.getPorcentagemDesconto();
			} else {
				locacao.setPorcentagemDesconto(0.30);
				return locacao.getPorcentagemDesconto();
			}
		} else {
			return 0.0;
		}
	}

}
