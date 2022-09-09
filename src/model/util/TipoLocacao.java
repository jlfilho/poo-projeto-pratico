package model.util;

import java.time.Duration;
import java.time.LocalDateTime;

import model.entities.Locacao;

public class TipoLocacao {
	private static final int DIAS_LONGO_PERIODO = 10;
	
	
	public static Boolean isLocacaoLongoPeriodo(LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {
		if (periodoEmDias(dataRetirada, dataDevolucao) > DIAS_LONGO_PERIODO) {
			return true; 			
		}
		return false;
	}
	
	public static Boolean isLocacaoDiaria(LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {
		if (periodoEmDias(dataRetirada, dataDevolucao) <= DIAS_LONGO_PERIODO) {
			return true; 			
		}
		return false;
	}
	
	public static Long periodoEmDias(LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {
		return (long) Math.ceil(Duration.between(dataRetirada, dataDevolucao).toMinutes()/(24.0*60.0));
	}
	
	public static Long periodoEmDias(Locacao locacao) {
		return periodoEmDias(locacao.getDataRetirada(),locacao.getDataDevolucao());
	}
	

}
