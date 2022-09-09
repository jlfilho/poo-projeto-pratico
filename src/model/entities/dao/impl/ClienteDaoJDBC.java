package model.entities.dao.impl;

import java.sql.Connection;
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
import db.DbIntegrityException;
import model.entities.Cliente;
import model.entities.Telefone;
import model.entities.dao.ClienteDao;

public class ClienteDaoJDBC implements ClienteDao {

	private Connection conn;

	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void cadastrar(Cliente cliente) {
		PreparedStatement st = null;
		PreparedStatement st2 = null;

		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement("INSERT INTO cliente " + "(nome, cpf, email) " + "VALUES " + "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, cliente.getNome());
			st.setString(2, cliente.getCpf());
			st.setString(3, cliente.getEmail());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					cliente.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}

			st2 = conn.prepareStatement("INSERT INTO telefone " + "(numero, Cliente_id) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			for (Telefone t : cliente.getTelefones()) {
				st2.setString(1, t.getNumero());
				st2.setInt(2, cliente.getId());
				st2.addBatch();
			}
			st2.executeBatch();

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbIntegrityException("A transação fez rollback! Devido ao erro " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Erro ao fazer rollback! Devido ao erro " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeStatement(st2);
		}

	}

	@Override
	public void atualizar(Cliente cliente) {
		PreparedStatement st = null;
		PreparedStatement st2 = null;

		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement("UPDATE cliente " + "SET nome = ?, cpf = ?, email = ? " + "WHERE id = ?");

			st.setString(1, cliente.getNome());
			st.setString(2, cliente.getCpf());
			st.setString(3, cliente.getEmail());
			st.setInt(4, cliente.getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}

			st2 = conn.prepareStatement("UPDATE telefone SET numero = ? WHERE id = ?");
			for (Telefone t : cliente.getTelefones()) {
				st2.setString(1, t.getNumero());
				st2.setInt(2, t.getId());
				st2.addBatch();
			}
			st2.executeBatch();

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbIntegrityException("A transação fez rollback! Devido ao erro " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Erro ao fazer rollback! Devido ao erro " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeStatement(st2);
		}

	}

	@Override
	// TODO
	public void excluirPorId(Integer id) {
		PreparedStatement st = null;
		PreparedStatement st2 = null;

		try {
			conn.setAutoCommit(false);

			Cliente c = buscarPorId(id);
			st2 = conn.prepareStatement("DELETE FROM telefone WHERE id = ?");

			for (Telefone t : c.getTelefones()) {
				st2.setInt(1, t.getId());
				st2.addBatch();
			}
			st2.executeBatch();

			st = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbIntegrityException("A transação fez rollback! Devido ao erro " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Erro ao fazer rollback! Devido ao erro " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeStatement(st2);
		}

	}

	@Override
	public Cliente buscarPorId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		PreparedStatement st2 = null;
		ResultSet rs2 = null;

		try {
			st = conn.prepareStatement("SELECT * FROM cliente " + "WHERE id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {
				Cliente cliente = instantiateCliente(rs);

				st2 = conn.prepareStatement("SELECT * FROM telefone " + "WHERE Cliente_id = ?");
				st2.setInt(1, id);

				rs2 = st2.executeQuery();

				List<Telefone> telefones = new ArrayList<>();
				Map<Integer, Telefone> map = new HashMap<>();

				while (rs2.next()) {

					Telefone tel = map.get(rs2.getInt("id"));

					if (tel == null) {
						tel = instantiateTelefone(rs2, cliente);
						map.put(rs2.getInt("id"), tel);
					}
					cliente.addTelefone(tel);
				}
				return cliente;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeStatement(st2);
			DB.closeResultSet(rs2);
		}
	}

	private Cliente instantiateCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setEmail(rs.getString("email"));
		return cliente;
	}

	private Telefone instantiateTelefone(ResultSet rs, Cliente cliente) throws SQLException {
		Telefone telefone = new Telefone();
		telefone.setId(rs.getInt("id"));
		telefone.setNumero(rs.getString("numero"));
		telefone.setCliente(cliente);
		return telefone;
	}

	@Override
	public List<Cliente> buscarTodos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		PreparedStatement st2 = null;
		ResultSet rs2 = null;

		try {
			st = conn.prepareStatement("SELECT * FROM cliente ORDER BY id");
			rs = st.executeQuery();

			List<Cliente> clientes = new ArrayList<>();

			while (rs.next()) {
				Cliente cliente = instantiateCliente(rs);

				st2 = conn.prepareStatement("SELECT * FROM telefone " + "WHERE Cliente_id = ?");
				st2.setInt(1, cliente.getId());

				rs2 = st2.executeQuery();

				Map<Integer, Telefone> map = new HashMap<>();

				while (rs2.next()) {
					Telefone tel = map.get(rs2.getInt("id"));
					if (tel == null) {
						tel = instantiateTelefone(rs2, cliente);
						map.put(rs2.getInt("id"), tel);
					}
					cliente.addTelefone(tel);
				}
				clientes.add(cliente);
			}

			return clientes;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
