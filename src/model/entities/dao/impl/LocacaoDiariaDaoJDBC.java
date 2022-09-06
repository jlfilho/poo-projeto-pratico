package model.entities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.entities.Carro;
import model.entities.Categoria;
import model.entities.Cliente;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.dao.CarroDao;
import model.entities.dao.ClienteDao;
import model.entities.dao.DaoFactory;

public class LocacaoDiariaDaoJDBC extends StaticLocacaoDao {

	public LocacaoDiariaDaoJDBC(Connection conn) {
		super(conn);
	}


	


	@Override
	public Locacao buscarPorId(Integer id) {
		CarroDao carroDao = DaoFactory.createCarroDao();
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = super.getConn().prepareStatement(
					"SELECT locacao.* "
							+ "FROM locacao WHERE id = ?");
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
			st = super.getConn().prepareStatement(
					"SELECT * FROM locacao WHERE diasPrevistoDevolucao is not null ORDER BY id");

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

	@Override
	public List<Locacao> buscarPorCliente(ClienteDao cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setLocacaoService(PreparedStatement st, LocacaoDiaria locacao) throws SQLException {
		st.setTimestamp(1, Timestamp.valueOf(locacao.getDataRetirada()));
		st.setTimestamp(2, Timestamp.valueOf(locacao.getDataDevolucao()));
		st.setLong(3, locacao.getDiasPrevistoDevolucao());
		st.setInt(4, locacao.getCliente().getId());
		st.setInt(5, locacao.getCarro().getId());
	}
	
	private Locacao instantiateLocacao(ResultSet rs) throws SQLException {
		LocacaoDiaria locacao = new LocacaoDiaria();
		locacao.setId(rs.getInt("id"));
		locacao.setDataRetirada(rs.getTimestamp("dataRetirada").toLocalDateTime());
		locacao.setDataDevolucao(rs.getTimestamp("dataDevolucao").toLocalDateTime());
		locacao.setDiasPrevistoDevolucao(rs.getLong("diasPrevistoDevolucao"));
		return locacao;
	}

}
