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
import model.entities.LocacaoLongoPeriodo;
import model.entities.dao.CarroDao;
import model.entities.dao.ClienteDao;
import model.entities.dao.DaoFactory;

public class LocacaoLongoPeriodoDaoJDBC extends StaticLocacaoDao {

	public LocacaoLongoPeriodoDaoJDBC(Connection conn) {
		super(conn);
	}



	@Override
	public List<Locacao> buscarPorCliente(ClienteDao cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
