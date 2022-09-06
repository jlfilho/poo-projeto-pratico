package model.entities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import db.DB;
import db.DbException;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;
import model.entities.dao.LocacaoDao;

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

}
