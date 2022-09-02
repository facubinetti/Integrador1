package com.tp1.factory;

import com.tp1.connection.ConexionDerby;
import com.tp1.connection.ConexionMySql;
import com.tp1.dao.PersonaDaoImplMySql;
import com.tp1.idao.IPersonaDao;

import java.sql.Connection;

public class DerbyDaoFactory extends DAOFactory {

	ConexionDerby conexionDerby = new ConexionDerby();
	
	@Override
	public IPersonaDao getCustomerDAO() {
		// TODO Auto-generated method stub
		return new PersonaDaoImplMySql(conexionDerby);
	}

}
