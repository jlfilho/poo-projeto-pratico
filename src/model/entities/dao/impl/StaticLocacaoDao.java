package model.entities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.entities.Carro;
import model.entities.Cliente;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;
import model.entities.dao.CarroDao;
import model.entities.dao.ClienteDao;
import model.entities.dao.DaoFactory;
import model.entities.dao.LocacaoDao;
import model.util.TipoLocacao;

public abstract class StaticLocacaoDao implements LocacaoDao {

	private Connection conn;

	public StaticLocacaoDao(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void cadastrar(Locacao locacao) {
		PreparedStatement st = null;

		try {

			if (locacao instanceof LocacaoDiaria) {
				st = getConn().prepareStatement("INSERT INTO locacao "
						+ "(dataRetirada, dataDevolucao, diasPrevistoDevolucao, Cliente_id, Carro_id) " + "VALUES "
						+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

				setLocacaoService(st, (LocacaoDiaria) locacao);
			} else {
				st = getConn().prepareStatement("INSERT INTO locacao "
						+ "(dataRetirada, dataDevolucao, porcentagemDesconto, Cliente_id, Carro_id) " + "VALUES "
						+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

				setLocacaoService(st, (LocacaoLongoPeriodo) locacao);
			}

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					locacao.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void atualizar(Locacao locacao) {
		PreparedStatement st = null;

		try {
			if (locacao instanceof LocacaoDiaria) {
				st = getConn().prepareStatement("UPDATE locacao SET dataRetirada = ?, dataDevolucao = ?, "
						+ "diasPrevistoDevolucao = ?, Cliente_id = ?, Carro_id = ? " + "WHERE (id = ?)");
				setLocacaoService(st, (LocacaoDiaria) locacao);
			} else {
				st = getConn().prepareStatement("UPDATE locacao SET dataRetirada = ?, dataDevolucao = ?, "
						+ "porcentagemDesconto = ?, Cliente_id = ?, Carro_id = ? " + "WHERE (id = ?)");

				setLocacaoService(st, (LocacaoLongoPeriodo) locacao);
			}
			st.setInt(6, locacao.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void excluirPorId(Integer id) {
		PreparedStatement st = null;

		try {
			st = getConn().prepareStatement("DELETE FROM locacao WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Locacao buscarPorId(Integer id) {
		CarroDao carroDao = DaoFactory.createCarroDao();
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = getConn().prepareStatement("SELECT locacao.* " + "FROM locacao WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Locacao locacao = instantiateLocacao(rs);
				Carro carro = carroDao.buscarPorId(rs.getInt("Carro_id"));
				locacao.setCarro(carro);
				Cliente cliente = clienteDao.buscarPorId(rs.getInt("Cliente_id"));
				locacao.setCliente(cliente);
				return locacao;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Locacao> buscarTodos() {
		CarroDao carroDao = DaoFactory.createCarroDao();
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConn()
					.prepareStatement("SELECT * FROM locacao ORDER BY id");

			rs = st.executeQuery();

			List<Locacao> locacoes = new ArrayList<>();
			Map<Integer, Carro> mapCarro = new HashMap<>();
			Map<Integer, Cliente> mapCliente = new HashMap<>();

			while (rs.next()) {
				Carro carro = mapCarro.get(rs.getInt("Carro_id"));
				if (carro == null) {
					carro = carroDao.buscarPorId(rs.getInt("Carro_id"));
					mapCarro.put(rs.getInt("Carro_id"), carro);
				}
				Cliente cliente = mapCliente.get(rs.getInt("Cliente_id"));
				if (cliente == null) {
					cliente = clienteDao.buscarPorId(rs.getInt("Cliente_id"));
					mapCliente.put(rs.getInt("Cliente_id"), cliente);
				}
				Locacao locacao = instantiateLocacao(rs);
				locacao.setCarro(carro);
				locacao.setCliente(cliente);
				locacoes.add(locacao);
			}
			return locacoes;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private void setLocacaoService(PreparedStatement st, LocacaoDiaria locacao) throws SQLException {
		st.setTimestamp(1, Timestamp.valueOf(locacao.getDataRetirada()));
		st.setTimestamp(2, Timestamp.valueOf(locacao.getDataDevolucao()));
		st.setLong(3, locacao.getDiasPrevistoDevolucao());
		st.setInt(4, locacao.getCliente().getId());
		st.setInt(5, locacao.getCarro().getId());
	}

	private void setLocacaoService(PreparedStatement st, LocacaoLongoPeriodo locacao) throws SQLException {
		st.setTimestamp(1, Timestamp.valueOf(locacao.getDataRetirada()));
		st.setTimestamp(2, Timestamp.valueOf(locacao.getDataDevolucao()));
		st.setDouble(3, locacao.getPorcentagemDesconto());
		st.setInt(4, locacao.getCliente().getId());
		st.setInt(5, locacao.getCarro().getId());
	}

	private Locacao instantiateLocacao(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id");
		LocalDateTime dataRetirada = rs.getTimestamp("dataRetirada").toLocalDateTime();
		LocalDateTime dataDevolucao = rs.getTimestamp("dataDevolucao").toLocalDateTime();
		Long diasPrevistoDevolucao = rs.getLong("diasPrevistoDevolucao");
		Double porcentagemDesconto = rs.getDouble("porcentagemDesconto");

		if (TipoLocacao.isLocacaoDiaria(dataRetirada, dataDevolucao)) {
			LocacaoDiaria locacao = new LocacaoDiaria();
			locacao.setId(id);
			locacao.setDataRetirada(dataRetirada);
			locacao.setDataDevolucao(dataDevolucao);
			locacao.setDiasPrevistoDevolucao(diasPrevistoDevolucao);
			return locacao;
		}
		LocacaoLongoPeriodo locacao = new LocacaoLongoPeriodo();
		locacao.setId(id);
		locacao.setDataRetirada(dataRetirada);
		locacao.setDataDevolucao(dataDevolucao);
		locacao.setPorcentagemDesconto(porcentagemDesconto);

		return locacao;

	}

}
