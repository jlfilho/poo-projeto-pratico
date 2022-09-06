package model.entities.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.entities.Carro;
import model.entities.Categoria;
import model.entities.dao.CarroDao;
import model.entities.enums.Cor;

public class CarroDaoJDBC implements CarroDao {

	private Connection conn;

	public CarroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void cadastrar(Carro carro) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO carro " + "(modelo, placa, cor, ano, dataAquisicao, Categoria_id) "
					+ "VALUES " + "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			setCarroService(st, carro);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					carro.setId(id);
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
	public void atualizar(Carro carro) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE carro "
					+ "SET modelo = ?, placa = ?, cor = ?, ano = ?, dataAquisicao = ?, Categoria_id = ? "
					+ "WHERE id = ?");
			

			setCarroService(st, carro);
			st.setInt(7, carro.getId());

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
			st = conn.prepareStatement("DELETE FROM carro WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Carro buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT carro.*,categoria.descricao as catDescricao, categoria.precoDiaria as catprecoDiaria  "
							+ "FROM carro INNER JOIN categoria " + "ON carro.Categoria_id = categoria.id "
							+ "WHERE carro.id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Categoria categoria = instantiateCategoria(rs);
				Carro carro = instantiateCarro(rs, categoria);
				return carro;
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
	public List<Carro> buscarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT carro.*, categoria.descricao as catDescricao, categoria.precoDiaria as catprecoDiaria "
							+ "FROM carro INNER JOIN categoria " + "ON carro.Categoria_id = categoria.id "
							+ "ORDER BY id");

			rs = st.executeQuery();

			List<Carro> carros = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();

			while (rs.next()) {

				Categoria categoria = map.get(rs.getInt("Categoria_id"));

				if (categoria == null) {
					categoria = instantiateCategoria(rs);
					map.put(rs.getInt("Categoria_id"), categoria);
				}

				Carro carro = instantiateCarro(rs, categoria);
				carros.add(carro);
			}
			return carros;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Carro> buscarPorCategoria(Categoria categoria) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT carro.*, categoria.descricao as catDescricao, categoria.precoDiaria as catprecoDiaria "
					+ "FROM carro INNER JOIN categoria "
					+ "ON carro.Categoria_id = categoria.id " 
					+ "WHERE Categoria_id = ? " + "ORDER BY id");

			st.setInt(1, categoria.getId());

			rs = st.executeQuery();

			List<Carro> carros = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();

			while (rs.next()) {

				Categoria cat = map.get(rs.getInt("Categoria_id"));

				if (cat == null) {
					cat = instantiateCategoria(rs);
					map.put(rs.getInt("Categoria_id"), cat);
				}

				Carro carro = instantiateCarro(rs, cat);
				carros.add(carro);
			}
			return carros;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private void setCarroService(PreparedStatement st, Carro carro) throws SQLException {
		st.setString(1, carro.getModelo());
		st.setString(2, carro.getPlaca());
		st.setString(3, carro.getCor().toString());
		st.setInt(4, carro.getAno());
		st.setDate(5, Date.valueOf(carro.getDataAquisicao()));
		st.setInt(6, carro.getCategoria().getId());
	}

	private Carro instantiateCarro(ResultSet rs, Categoria categoria) throws SQLException {
		Carro carro = new Carro();
		carro.setId(rs.getInt("id"));
		carro.setModelo(rs.getString("modelo"));
		carro.setPlaca(rs.getString("placa"));
		carro.setCor(Cor.valueOf(rs.getString("cor")));
		carro.setAno(rs.getInt("ano"));
		carro.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate());
		carro.setCategoria(categoria);
		return carro;
	}

	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setId(rs.getInt("Categoria_id"));
		categoria.setDescricao(rs.getString("catDescricao"));
		categoria.setPrecoDiaria(rs.getDouble("catprecoDiaria"));
		return categoria;

	}

}
