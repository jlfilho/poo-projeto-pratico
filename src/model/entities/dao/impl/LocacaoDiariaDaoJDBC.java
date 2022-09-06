package model.entities.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.dao.ClienteDao;

public class LocacaoDiariaDaoJDBC extends StaticLocacaoDao {

	public LocacaoDiariaDaoJDBC(Connection conn) {
		super(conn);
	}


	@Override
	public List<Locacao> buscarPorCliente(ClienteDao cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
