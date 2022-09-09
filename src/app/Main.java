package app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Carro;
import model.entities.Categoria;
import model.entities.Cliente;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;
import model.entities.Telefone;
import model.entities.dao.CarroDao;
import model.entities.dao.CategoriaDao;
import model.entities.dao.ClienteDao;
import model.entities.dao.DaoFactory;
import model.entities.dao.LocacaoDao;
import model.entities.enums.Cor;
import model.service.ServicoPagamento;
import model.util.TipoLocacao;

public class Main {

	public static void main(String[] args) {
		menuPrincipal();
	}

	public static void menuPrincipal() {
		// clrscr();

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Short op = 0;

		do {

			System.out.println("\n\n");
			System.out.println("+-------------------------------------------+");
			System.out.println("|       SISTEMA DE LOCAÇÃO DE CARROS        |");
			System.out.println("+-------------------------------------------+");
			System.out.println("+-------------------------------------------+");
			System.out.println("|        Menu Principal                     |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| 1 - Menu Categoria                        |");
			System.out.println("| 2 - Menu Carro                            |");
			System.out.println("| 3 - Menu Cliente                          |");
			System.out.println("| 4 - Menu Locação                          |");
			System.out.println("| 9 - Sair                                  |");
			System.out.println("+-------------------------------------------+");

			try {
				System.out.print("Opção escolhida: ");
				op = sc.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("Entre com um valor numérico!");
			}
			sc.nextLine();

			switch (op) {
			case 1: {
				menuCategoria();
				break;
			}
			case 2: {
				menuCarro();
				break;
			}
			case 3: {
				menuCliente();
				break;
			}
			case 4: {
				menuLocacao();
				break;
			}
			case 9: {
				System.out.println("\nSistema fechado com sucesso!");
				break;
			}

			default:
				System.out.print("\nOpção inválida!");
				System.out.println("\n... Pressione Enter para continuar ...");
				sc.nextLine();
			}

		} while (op != 9);

	}

	public static void menuCategoria() {
		// clrscr();

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Short op = 0;
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

		do {
			System.out.println("\n\n");
			System.out.println("+-------------------------------------------+");
			System.out.println("|       SISTEMA DE LOCAÇÃO DE CARROS        |");
			System.out.println("+-------------------------------------------+");
			System.out.println("+-------------------------------------------+");
			System.out.println("|        Menu Categoria                     |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| 1 - Cadastar Categoria                    |");
			System.out.println("| 2 - Listar Todas Categorias               |");
			System.out.println("| 3 - Editar Categoria                      |");
			System.out.println("| 4 - Excluir Categoria                     |");
			System.out.println("| 9 - Voltar ao menu principal              |");
			System.out.println("+-------------------------------------------+");

			try {
				System.out.print("Opção escolhida: ");
				op = sc.nextShort();
			} catch (InputMismatchException e) {
				System.out.println("Entre com um valor numérico!");
			}
			sc.nextLine();

			switch (op) {
			case 1: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Cadastro de Categoria              |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Descrição: ");
					String descricao = sc.nextLine();
					System.out.print("Preço da Diária: ");
					double precoDiaria = sc.nextDouble();
					sc.nextLine();
					Categoria categoria = new Categoria(null, descricao, precoDiaria);

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						categoriaDao.cadastrar(categoria);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}

				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
					sc.nextLine();
				}
				sc.nextLine();
				break;
			}
			case 2: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Lista de Categorias                |");
					System.out.println("+-------------------------------------------+");

					for (Categoria c : categoriaDao.buscarTodos()) {
						System.out.println("+-------------------------------------------+");
						System.out.println("Id: " + c.getId() + ", Descrição: " + c.getDescricao() + ", Preço Diária: "
								+ c.getPrecoDiaria());
						System.out.println("+-------------------------------------------+");
					}
					System.out.println("... Pressione Enter para continuar ...");
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
					sc.nextLine();
				}
				sc.nextLine();

				break;
			}
			case 3: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Editar Categoria                   |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o id da categoria a editar: ");
					int id = sc.nextInt();
					sc.nextLine();
					Categoria c = categoriaDao.buscarPorId(id);
					System.out.println("+-------------------------------------------+");
					System.out.println("Id: " + c.getId() + ", Descrição: " + c.getDescricao() + ", Preço Diária: "
							+ c.getPrecoDiaria());
					System.out.println("+-------------------------------------------+");
					System.out.println("Entre com os dados para atualizar");
					System.out.print("Nova descrição: ");
					c.setDescricao(sc.nextLine());
					System.out.print("Novo Preço da Diária: ");
					c.setPrecoDiaria(sc.nextDouble());

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						categoriaDao.atualizar(c);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
					sc.nextLine();
				}
				sc.nextLine();

				break;
			}
			case 4: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Excluir Categoria                   |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o id da categoria a excluir: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						categoriaDao.excluirPorId(id);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
					sc.nextLine();
				}
				sc.nextLine();

				break;
			}
			case 9: {

				break;
			}

			default:
				System.out.print("\nOpção inválida!");
				System.out.println("\n... Pressione Enter para continuar ...");
				sc.nextLine();
			}
		} while (op != 9);

	}

	public static void menuCarro() {
		// clrscr();

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Short op = 0;
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		CarroDao carroDao = DaoFactory.createCarroDao();

		do {
			System.out.println("\n\n");
			System.out.println("+-------------------------------------------+");
			System.out.println("|       SISTEMA DE LOCAÇÃO DE CARROS        |");
			System.out.println("+-------------------------------------------+");
			System.out.println("+-------------------------------------------+");
			System.out.println("|        Menu Carro                         |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| 1 - Cadastar Carro                        |");
			System.out.println("| 2 - Listar Todos os Carros                |");
			System.out.println("| 3 - Listar por Categoria                  |");
			System.out.println("| 4 - Editar Carros                         |");
			System.out.println("| 5 - Excluir Carro                         |");
			System.out.println("| 9 - Voltar ao menu principal              |");
			System.out.println("+-------------------------------------------+");

			try {
				System.out.print("Opção escolhida: ");
				op = sc.nextShort();
			} catch (RuntimeException e) {
				System.out.println("\n\nErro: " + e.getMessage());
			}
			sc.nextLine();

			switch (op) {
			case 1: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Cadastro de Carro                  |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Modelo: ");
					String modelo = sc.nextLine();
					System.out.print("Placa: ");
					String placa = sc.nextLine();
					System.out.print("Cor (BRANCA, PRETA, CINZA ou VERMELHA): ");
					Cor cor = Cor.valueOf(sc.nextLine());
					System.out.print("Ano: ");
					int ano = sc.nextInt();
					System.out.print("Data da Aquisição (dd/mm/yyyy): ");
					LocalDate dataAquisicao = LocalDate.parse(sc.next(), fmt);
					System.out.print("Código da Categoria: ");
					int idCategoria = sc.nextInt();
					sc.nextLine();

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						carroDao.cadastrar(
								new Carro(null, modelo, placa, cor, ano, dataAquisicao, new Categoria(idCategoria)));
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 2: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Lista de Carros                    |");
					System.out.println("+-------------------------------------------+");

					for (Carro c : carroDao.buscarTodos()) {
						System.out.println("+-------------------------------------------+");
						System.out.println(c);
						System.out.println("+-------------------------------------------+");
					}
					System.out.println("... Pressione Enter para continuar ...");
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 3: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Lista Carros por Categoria         |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o Código da Categoria: ");
					int idCategoria = sc.nextInt();
					sc.nextLine();
					for (Carro c : carroDao.buscarPorCategoria(new Categoria(idCategoria, null, null))) {
						System.out.println("+-------------------------------------------+");
						System.out.println(c);
						System.out.println("+-------------------------------------------+");
					}
					System.out.println("... Pressione Enter para continuar ...");
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 4: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Editar Carro                       |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o Código do carro: ");
					int idCarro = sc.nextInt();
					sc.nextLine();
					Carro c = carroDao.buscarPorId(idCarro);
					System.out.println("+-------------------------------------------+");
					System.out.println(c);
					System.out.println("+-------------------------------------------+");
					System.out.println("Entre com os dados do carro para atualização: ");
					System.out.print("Modelo: ");
					c.setModelo(sc.nextLine());
					System.out.print("Placa: ");
					c.setPlaca(sc.nextLine());
					System.out.print("Cor (BRANCA, PRETA, CINZA ou VERMELHA): ");
					c.setCor(Cor.valueOf(sc.nextLine()));
					System.out.print("Ano: ");
					c.setAno(sc.nextInt());
					System.out.print("Data da Aquisição (dd/mm/yyyy): ");
					c.setDataAquisicao(LocalDate.parse(sc.next(), fmt));
					System.out.print("Código da Categoria: ");
					c.setCategoria(new Categoria(sc.nextInt(), null, null));
					sc.nextLine();

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						carroDao.atualizar(c);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 5: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Excluir Carro                      |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o id do carro a excluir: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						carroDao.excluirPorId(id);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 9: {

				break;
			}
			default:
				System.out.print("\nOpção inválida!");
				System.out.println("\n... Pressione Enter para continuar ...");
				sc.nextLine();
			}
		} while (op != 9);

	}

	public static void menuCliente() {
		// clrscr();

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Short op = 0;
		ClienteDao clienteDao = DaoFactory.createClienteDao();

		do {
			System.out.println("\n\n");
			System.out.println("+-------------------------------------------+");
			System.out.println("|       SISTEMA DE LOCAÇÃO DE CARROS        |");
			System.out.println("+-------------------------------------------+");
			System.out.println("+-------------------------------------------+");
			System.out.println("|        Menu Cliente                       |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| 1 - Cadastar Cliente                      |");
			System.out.println("| 2 - Listar Todos os Clientes              |");
			System.out.println("| 3 - Editar Clientes                       |");
			System.out.println("| 4 - Excluir Cliente                       |");
			System.out.println("| 9 - Voltar ao menu principal              |");
			System.out.println("+-------------------------------------------+");
			try {
				System.out.print("Opção escolhida: ");
				op = sc.nextShort();
				sc.nextLine();
			} catch (RuntimeException e) {
				System.out.println("\n\nErro: " + e.getMessage());
			}

			switch (op) {
			case 1: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Cadastro de Cliente                |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Nome: ");
					String nome = sc.nextLine();
					System.out.print("CPF: ");
					String cpf = sc.nextLine();
					System.out.print("E-mail: ");
					String email = sc.nextLine();
					Cliente cliente = new Cliente(null, nome, cpf, email);

					System.out.print("Informe a quantidade de telefones a inserir: ");
					int n = sc.nextInt();
					sc.nextLine();
					for (int i = 1; i <= n; i++) {
						System.out.printf("Telefone #%d: ", i);
						String numero = sc.nextLine();
						cliente.addTelefone(new Telefone(null, numero, cliente));
					}

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						clienteDao.cadastrar(cliente);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 2: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Lista de Clientes                  |");
					System.out.println("+-------------------------------------------+");

					for (Cliente c : clienteDao.buscarTodos()) {
						System.out.println("+-------------------------------------------+");
						System.out.println(c);
						for (Telefone t : c.getTelefones()) {
							System.out.println(t);
						}
						System.out.println("+-------------------------------------------+");
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				System.out.println("... Pressione Enter para continuar ...");
				sc.nextLine();

				break;
			}
			case 3: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Editar de Cliente             |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Informe o código do cliente para atualizar: ");
					Cliente cliente = clienteDao.buscarPorId(sc.nextInt());
					sc.nextLine();
					System.out.println("+-------------------------------------------+");
					System.out.println(cliente);
					for (Telefone t : cliente.getTelefones()) {
						System.out.println(t);
					}
					System.out.println("+-------------------------------------------+");
					System.out.print("Nome: ");
					cliente.setNome(sc.nextLine());
					System.out.print("CPF: ");
					cliente.setCpf(sc.nextLine());
					System.out.print("E-mail: ");
					cliente.setEmail(sc.nextLine());
					int i = 0;
					for (Telefone t : cliente.getTelefones()) {
						System.out.printf("Telefone #%d: ", t.getId());
						String numero = sc.nextLine();
						cliente.getTelefones().set(i, new Telefone(t.getId(), numero, cliente));
						i++;
					}

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						clienteDao.atualizar(cliente);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 4: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Excluir Cliente                    |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o id do cliente a excluir: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						clienteDao.excluirPorId(id);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 9: {

				break;
			}
			default:
				System.out.print("\nOpção inválida!");
				System.out.println("\n... Pressione Enter para continuar ...");
				sc.nextLine();
			}
		} while (op != 9);

	}

	public static void menuLocacao() {
		// clrscr();

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Short op = 0;
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocacaoDao locacaoDao = DaoFactory.createLocacaoDao();

		do {
			System.out.println("\n\n");
			System.out.println("+-------------------------------------------+");
			System.out.println("|       SISTEMA DE LOCAÇÃO DE CARROS        |");
			System.out.println("+-------------------------------------------+");
			System.out.println("+-------------------------------------------+");
			System.out.println("|        Menu Locação                       |");
			System.out.println("+-------------------------------------------+");
			System.out.println("| 1 - Nova Locação                          |");
			System.out.println("| 2 - Editar Locação                        |");
			System.out.println("| 3 - Devolução de Carro                    |");
			System.out.println("| 4 - Listar Todas as Locações              |");
			System.out.println("| 5 - Listar Locação por Cliente            |");
			System.out.println("| 6 - Excluir Locação                       |");
			System.out.println("| 9 - Voltar ao menu principal              |");
			System.out.println("+-------------------------------------------+");
			try {
				System.out.print("Opção escolhida: ");
				op = sc.nextShort();
				sc.nextLine();
			} catch (RuntimeException e) {
				System.out.println("\n\nErro: " + e.getMessage());
			}

			switch (op) {
			case 1: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Nova Locação de Carros             |");
					System.out.println("+-------------------------------------------+");

					System.out.print("Entre com o código do cliente: ");
					int idCliente = sc.nextInt();
					sc.nextLine();

					System.out.print("Entre com o código do carro: ");
					int idCarro = sc.nextInt();
					sc.nextLine();

					System.out.print("Data da Retirada (dd/MM/yyyy HH:mm): ");
					LocalDateTime dataRetirada = LocalDateTime.parse(sc.nextLine(), fmt);

					System.out.print("Data Prevista para Devolução (dd/MM/yyyy HH:mm): ");
					LocalDateTime dataDevolução = LocalDateTime.parse(sc.nextLine(), fmt);
					Locacao locacao;
					if (TipoLocacao.isLocacaoDiaria(dataRetirada, dataDevolução)) {
						locacao = new LocacaoDiaria(null, dataRetirada, dataDevolução, new Carro(idCarro),
								new Cliente(idCliente));
					} else {
						locacao = new LocacaoLongoPeriodo(null, dataRetirada, dataDevolução, new Carro(idCarro),
								new Cliente(idCliente));
					}

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						locacaoDao.cadastrar(locacao);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();
				break;
			}
			case 2: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Editar Locação                     |");
					System.out.println("+-------------------------------------------+");
					
					System.out.print("Entre com o código da locação: ");
					int idLocacao = sc.nextInt();
					sc.nextLine();
					
					Locacao locacao = locacaoDao.buscarPorId(idLocacao);
					System.out.println("+-------------------------------------------+");
					System.out.println(locacao);
					System.out.println("+-------------------------------------------+");
					
					System.out.println("Entre os dados para atualizar: ");
					System.out.print("Código do cliente: ");
					int idCliente = sc.nextInt();
					sc.nextLine();

					System.out.print("Código do carro: ");
					int idCarro = sc.nextInt();
					sc.nextLine();

					System.out.print("Data da Retirada (dd/MM/yyyy HH:mm): ");
					LocalDateTime dataRetirada = LocalDateTime.parse(sc.nextLine(), fmt);

					System.out.print("Data Prevista para Devolução (dd/MM/yyyy HH:mm): ");
					LocalDateTime dataDevolução = LocalDateTime.parse(sc.nextLine(), fmt);
					if (TipoLocacao.isLocacaoDiaria(dataRetirada, dataDevolução)) {
						locacao = new LocacaoDiaria(idLocacao, dataRetirada, dataDevolução, new Carro(idCarro),
								new Cliente(idCliente));
					} else {
						locacao = new LocacaoLongoPeriodo(idLocacao, dataRetirada, dataDevolução, new Carro(idCarro),
								new Cliente(idCliente));
					}

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						locacaoDao.atualizar(locacao);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();
				break;
			}
			case 3: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Devolução de Carro                 |");
					System.out.println("+-------------------------------------------+");

					System.out.print("Entre com o código da Locação: ");
					int idLocacao = sc.nextInt();
					sc.nextLine();
					Locacao loc = locacaoDao.buscarPorId(idLocacao);
					if (loc == null) {
						System.out.println("Não há locação cadastrada com este cídogo!");
					} else {
						System.out.print("Data da Devolução (dd/MM/yyyy HH:mm): ");
						loc.setDataDevolucao(LocalDateTime.parse(sc.nextLine(), fmt));
						Locacao locacao;
						if (TipoLocacao.isLocacaoDiaria(loc.getDataRetirada(), loc.getDataDevolucao())) {
							locacao = new LocacaoDiaria(loc.getId(), loc.getDataRetirada(), loc.getDataDevolucao(),
									loc.getCarro(), loc.getCliente());
						} else {
							locacao = new LocacaoLongoPeriodo(loc.getId(), loc.getDataRetirada(),
									loc.getDataDevolucao(), loc.getCarro(), loc.getCliente());
						}
						String nota = ServicoPagamento.gerarNota(locacao);
						System.out.printf(nota);

						locacaoDao.atualizar(locacao);
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}

				System.out.println("\n... Pressione Enter para continuar ...");
				sc.nextLine();

				break;
			}
			case 4: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Lista de Locações                  |");
					System.out.println("+-------------------------------------------+");

					for (Locacao l : locacaoDao.buscarTodos()) {
						System.out.println("+-------------------------------------------+");
						System.out.println(l);
						System.out.println("+-------------------------------------------+");
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				System.out.println("... Pressione Enter para continuar ...");
				sc.nextLine();

				break;
			}
			case 5: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Lista de Locações                  |");
					System.out.println("+-------------------------------------------+");

					System.out.print("Entre com o código do cliente: ");
					int idCliente = sc.nextInt();
					sc.nextLine();

					for (Locacao l : locacaoDao.buscarPorCliente(new Cliente(idCliente))) {
						System.out.println("+-------------------------------------------+");
						System.out.println(l);
						System.out.println("+-------------------------------------------+");
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				System.out.println("... Pressione Enter para continuar ...");
				sc.nextLine();

				break;
			}
			case 6: {
				try {
					System.out.println("+-------------------------------------------+");
					System.out.println("|        Excluir Locação                    |");
					System.out.println("+-------------------------------------------+");
					System.out.print("Entre com o id da locação a excluir: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Confirma a operação? s/n: ");
					String r = sc.next();

					if (r.toLowerCase().charAt(0) == 's') {
						locacaoDao.excluirPorId(id);
						System.out.println("\nOperação realizada com sucesso.");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					} else {
						System.out.println("\nOperação não realizada!");
						System.out.println("\n... Pressione Enter para continuar ...");
						sc.nextLine();
					}
				} catch (RuntimeException e) {
					System.out.println("\n\nErro: " + e.getMessage());
				}
				sc.nextLine();

				break;
			}
			case 9: {

				break;
			}
			default:
				System.out.print("\nOpção inválida!");
				System.out.println("\n... Pressione Enter para continuar ...");
				sc.nextLine();
			}
		} while (op != 9);

	}

	public static void clrscr() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
