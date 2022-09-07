package app;

import model.entities.Locacao;
import model.entities.dao.CarroDao;
import model.entities.dao.CategoriaDao;
import model.entities.dao.ClienteDao;
import model.entities.dao.DaoFactory;
import model.entities.dao.LocacaoDao;

public class Programa {

	public static void main(String[] args) {

		CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

		// CADASTRAR
		/*
		 * Categoria categoria = new Categoria(null, "Econômico Sedan com ar", 200.00);
		 * categoriaDao.cadastrar(categoria);
		 */

		// ATUALIZAR

		/*
		 * Categoria categoria = new Categoria(9, "Econômico Sedan c/ ar", 220.00);
		 * categoriaDao.atualizar(categoria);
		 */

		// categoriaDao.excluirPorId(9);

		// LISTAR TODOS
		/*
		 * List<Categoria> categorias = categoriaDao.buscarTodos();
		 * 
		 * for (Categoria c : categorias) { System.out.println( "Id: " + c.getId() +
		 * ", Descrição: " + c.getDescricao() + ", Preço Diária: " +
		 * c.getPrecoDiaria()); }
		 */

		// CADASTRAR
		CarroDao carroDao = DaoFactory.createCarroDao();

		/*
		 * Carro carro = new Carro(null,"Audi A4 2.0 Turbo", "NAC-8339", Cor.BRANCA,
		 * 2022, LocalDate.parse("2023-09-01"), new Categoria(8, null, null));
		 * carroDao.cadastrar(carro);
		 */

		// ATUALIZAR

		/*
		 * Carro carro = new Carro(14, "Audi A4 2.0 Turbo", "NAC-8339", Cor.BRANCA,
		 * 2022, LocalDate.parse("2022-09-01"), new Categoria(8, null, null));
		 * carroDao.atualizar(carro);
		 */

		// BUSCAR POR ID
		/*
		 * carro = carroDao.buscarPorId(14); System.out.println(carro);
		 */

		// buscar todos
		/*
		 * for (Carro c : carroDao.buscarTodos()) { System.out.println(c); }
		 */

		// buscar por categoria
		/*
		 * Categoria categoria = categoriaDao.buscarPorId(3); for (Carro c :
		 * carroDao.buscarPorCategoria(categoria)) { System.out.println(c); }
		 */

		// CLIENTE
		ClienteDao clienteDao = DaoFactory.createClienteDao();

		// cadastrar
		/*
		 * Cliente cliente = new Cliente(13, "Kaergo Rodrigues", "41262978041",
		 * "Kaergo@gmail.com".toLowerCase()); cliente.addTelefone(new Telefone(14,
		 * "92995563670",cliente)); cliente.addTelefone(new Telefone(15,
		 * "92984816685",cliente)); cliente.addTelefone(new Telefone(16,
		 * "92995432503",cliente));
		 * 
		 * clienteDao.atualizar(cliente);
		 */

		// Buscar por ID
		/*
		 * Cliente cliente = clienteDao.buscarPorId(13); System.out.println(cliente);
		 * for (Telefone t : cliente.getTelefones()) { System.out.println(t); }
		 */

		// Buscar todos
		/*
		 * for (Cliente c : clienteDao.buscarTodos()) { System.out.println(c); for
		 * (Telefone t : c.getTelefones()) { System.out.println(t); } }
		 */

		// Locação

		/*
		 * Locacao locacao = new LocacaoDiaria(15,
		 * LocalDateTime.parse("2022-09-05T11:00:00"),
		 * LocalDateTime.parse("2022-09-08T12:01:00"), carroDao.buscarPorId(14),
		 * clienteDao.buscarPorId(13)); StaticLocacaoDao locacaoDao =
		 * DaoFactory.createLocacaoDao(); locacaoDao.atualizar(locacao);
		 * 
		 * locacao = new LocacaoLongoPeriodo(16,
		 * LocalDateTime.parse("2022-09-05T11:00:00"),
		 * LocalDateTime.parse("2022-10-06T12:01:00"), carroDao.buscarPorId(14),
		 * clienteDao.buscarPorId(13), 0.15); locacaoDao =
		 * DaoFactory.createLocacaoLongoPeriodoDao(); locacaoDao.atualizar(locacao);
		 */

		// System.out.println(locacao);

		/*
		 * Locacao locacao = new LocacaoDiaria(9,
		 * LocalDateTime.parse("2022-09-05T11:00:00"),
		 * LocalDateTime.parse("2022-09-10T12:01:00"), carroDao.buscarPorId(14),
		 * clienteDao.buscarPorId(13)); StaticLocacaoDao locacaoDao =
		 * DaoFactory.createLocacaoDao(); //System.out.println(locacao);
		 * locacaoDao.atualizar(locacao);
		 */
		// Excluit por id
		/*
		 * StaticLocacaoDao locacaoDao = DaoFactory.createLocacaoDao();
		 * locacaoDao.excluirPorId(4);
		 */
		// Buscar por id

		/*
		 * StaticLocacaoDao locacaoDao = DaoFactory.createLocacaoDao(); Locacao
		 * locacao = locacaoDao.buscarPorId(15); System.out.println(locacao);
		 * 
		 * locacao = locacaoDao.buscarPorId(16); System.out.println(locacao);
		 */

		LocacaoDao locacaoDao = DaoFactory.createLocacaoDao();
		for (Locacao l : locacaoDao.buscarPorCliente(clienteDao.buscarPorId(13))) {
			System.out.println(l);
		}

		// Locação Longa

		/*
		 * Locacao locacao = new LocacaoLongoPeriodo(null,
		 * LocalDateTime.parse("2022-09-05T11:00:00"),
		 * LocalDateTime.parse("2022-12-06T12:01:00"), carroDao.buscarPorId(4),
		 * clienteDao.buscarPorId(8), 0.07); StaticLocacaoDao locacaoDao =
		 * DaoFactory.createLocacaoLongoPeriodoDao(); locacaoDao.cadastrar(locacao);
		 */
		// System.out.println(locacao);

		/*
		 * Locacao locacao = new LocacaoLongoPeriodo(12,
		 * LocalDateTime.parse("2022-09-05T11:00:00"),
		 * LocalDateTime.parse("2022-11-10T12:01:00"), carroDao.buscarPorId(6),
		 * clienteDao.buscarPorId(8), 0.12); StaticLocacaoDao locacaoDao =
		 * DaoFactory.createLocacaoLongoPeriodoDao(); // System.out.println(locacao);
		 * locacaoDao.atualizar(locacao);
		 */

		// Excluit por id

		/*
		 * StaticLocacaoDao locacaoDao = DaoFactory.createLocacaoDao();
		 * locacaoDao.excluirPorId(4);
		 */

		// Buscar por id
		/*
		 * StaticLocacaoDao locacaoDao = DaoFactory.createLocacaoDao(); Locacao
		 * locacao = locacaoDao.buscarPorId(5); System.out.println(locacao);
		 */

		/*
		 * StaticLocacaoDao locacaoDao = DaoFactory.createLocacaoDao(); for
		 * (Locacao l: locacaoDao.buscarTodos()) { System.out.println(l); }
		 */
	}

}
