package model.entities.dao;

import db.DB;
import model.entities.dao.impl.CarroDaoJDBC;
import model.entities.dao.impl.CategoriaDaoJDBC;
import model.entities.dao.impl.ClienteDaoJDBC;
import model.entities.dao.impl.LocacaoDiariaDaoJDBC;
import model.entities.dao.impl.LocacaoLongoPeriodoDaoJDBC;
import model.entities.dao.impl.StaticLocacaoDao;

public class DaoFactory {
	
	public static CategoriaDao createCategoriaDao() {
		return new CategoriaDaoJDBC(DB.getConnection());
	}
	
	public static CarroDao createCarroDao() {
		return new CarroDaoJDBC(DB.getConnection());
	}
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static StaticLocacaoDao createLocacaoDiariaDao() {
		return new LocacaoDiariaDaoJDBC(DB.getConnection());
	}
	
	public static StaticLocacaoDao createLocacaoLongoPeriodoDao() {
		return new LocacaoLongoPeriodoDaoJDBC(DB.getConnection());
	}
	
}
