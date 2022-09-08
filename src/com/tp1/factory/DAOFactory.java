package com.tp1.factory;

import com.tp1.dao.ClienteDaoImplMySql;
import com.tp1.dao.FacturaDaoImplMySql;
import com.tp1.dao.FacturaProductoDaoImplMySql;
import com.tp1.dao.ProductoDaoImplMySql;

public abstract class DAOFactory {

	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	
	public abstract ClienteDaoImplMySql getClienteDAO();
	public abstract FacturaDaoImplMySql getFacturaDAO();
	public abstract ProductoDaoImplMySql getProductoDAO();
	public abstract FacturaProductoDaoImplMySql getFacturaProductoDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC :
				return new MySqlDaoFactory();
			case DERBY_JDBC:
				return new DerbyDaoFactory();
			default: return null;
		}
	}
	
	
	
	
}
 