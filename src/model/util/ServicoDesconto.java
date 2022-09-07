package model.util;

import java.time.LocalDateTime;

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

}
