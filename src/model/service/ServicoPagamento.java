package model.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.entities.Locacao;
import model.util.TipoLocacao;

public class ServicoPagamento {

	private static double calcularPagamento(Locacao locacao) {
		return locacao.getCarro().getCategoria().getPrecoDiaria()
				* TipoLocacao.periodoEmDias(locacao.getDataRetirada(), locacao.getDataDevolucao());

	}

	private static double calcularDesconto(Locacao locacao) {
		return calcularPagamento(locacao) * ServicoDesconto.porcentagemDesconto(locacao);
	}

	public static String gerarNota(Locacao locacao) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		StringBuilder sb = new StringBuilder();

		sb.append("\n\n+-------------------------------------------+\n");
		sb.append("|   NOTA DE PAGAMENTO DE LOCAÇÃO DE CARRO   |\n");
		sb.append("+-------------------------------------------+\n");
		sb.append("CLIENTE: " + locacao.getCliente().getNome() + "\n");
		sb.append("CPF: " + locacao.getCliente().getCpf() + "\n");
		sb.append("TELEFONE: " + locacao.getCliente().getTelefones().get(0).getNumero() + "\n");
		sb.append("+-------------------------------------------+\n");
		sb.append("CARRO CAT.: " + locacao.getCarro().getCategoria().getDescricao() + "\n");
		sb.append("MODELO: " + locacao.getCarro().getModelo() + "\n");
		sb.append("PLACA: " + locacao.getCarro().getPlaca() + "     ");
		sb.append("ANO: " + locacao.getCarro().getAno() + "\n");
		sb.append("+-------------------------------------------+\n");
		sb.append("DATA RETIRADA: " + locacao.getDataRetirada().format(dtf) + "\n");
		sb.append("DATA DEVOLUÇÃO: " + locacao.getDataDevolucao().format(dtf) + "\n");
		sb.append("DIAS DE LOCAÇÃO: " + TipoLocacao.periodoEmDias(locacao) + " dia(s)\n");
		sb.append("+-------------------------------------------+\n");
		sb.append("DIÁRIA: R$" + String.format(" %.2f", locacao.getCarro().getCategoria().getPrecoDiaria()) + "\n");
		sb.append("VALOR DAS DIÁRIAS: R$" + String.format(" %.2f", calcularPagamento(locacao)) + "\n");
		sb.append("DESCONTO: R$" + String.format(" %.2f", calcularDesconto(locacao)) + "\n");
		sb.append("+-------------------------------------------+\n");
		sb.append("TOTAL: R$" + String.format(" %.2f", calcularPagamento(locacao) - calcularDesconto(locacao)) + "\n");
		sb.append("+-------------------------------------------+\n");
		return sb.toString();
	}

}