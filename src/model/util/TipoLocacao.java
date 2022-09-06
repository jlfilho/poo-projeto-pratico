package model.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TipoLocacao {
	
	public static Boolean isLocacaoLongoPeriodo(LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {
		if (Duration.between(dataRetirada, dataDevolucao).toMinutes()/(1440.0+60.0) > 10) {
			return true; 			
		}
		return false;
	}
	
	public static Boolean isLocacaoDiaria(LocalDateTime dataRetirada, LocalDateTime dataDevolucao) {
		if (Duration.between(dataRetirada, dataDevolucao).toMinutes()/(1440.0+60.0) <= 10) {
			return true; 			
		}
		return false;
	}
	

}
