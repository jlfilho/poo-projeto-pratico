package model.util;

import java.time.Duration;
import java.time.LocalDateTime;

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
		return (long) Math.ceil(Duration.between(dataRetirada, dataDevolucao).toMinutes()/(1440.0+60.0));
	}
	

}
