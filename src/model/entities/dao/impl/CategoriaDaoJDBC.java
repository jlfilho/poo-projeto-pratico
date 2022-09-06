package model.entities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Carro;
import model.entities.Categoria;
import model.entities.dao.CategoriaDao;

public class CategoriaDaoJDBC implements CategoriaDao {

	private Connection conn;
	
	public CategoriaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void cadastrar(Categoria categoria) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO categoria " + "(descricao, precoDiaria) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, categoria.getDescricao());
			st.setDouble(2, categoria.getPrecoDiaria());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					categoria.setId(id);
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
	public void atualizar(Categoria categoria) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE categoria " + "SET descricao = ?, precoDiaria = ? " + "WHERE Id = ?");

			st.setString(1, categoria.getDescricao());
			st.setDouble(2, categoria.getPrecoDiaria());
			st.setInt(3, categoria.getId());

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
			st = conn.prepareStatement("DELETE FROM categoria WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public Categoria buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM categoria "
							+ "WHERE id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Categoria categoria = instantiateCategoria(rs);
				return categoria;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setId(rs.getInt("id"));
		categoria.setDescricao(rs.getString("descricao"));
		categoria.setPrecoDiaria(rs.getDouble("precoDiaria"));
		return categoria;
	}

	@Override
	public List<Categoria> buscarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM categoria ORDER BY descricao");
			rs = st.executeQuery();

			List<Categoria> list = new ArrayList<>();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setPrecoDiaria(rs.getDouble("precoDiaria"));
				list.add(categoria);
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
