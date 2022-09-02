package com.tp1.factory;

import com.tp1.connection.ConexionMySql;
import com.tp1.dao.PersonaDaoImplMySql;
import com.tp1.idao.IPersonaDao;

public class MySqlDaoFactory extends DAOFactory {

	ConexionMySql conexionMySql = new ConexionMySql();
	
	@Override
	public IPersonaDao getCustomerDAO() {
		// TODO Auto-generated method stub
		return new PersonaDaoImplMySql(conexionMySql);
	}

}
