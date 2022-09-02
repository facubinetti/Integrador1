package com.tp1.factory;

import com.tp1.dao.ClienteDaoImpl;
import com.tp1.dao.FacturaDaoImpl;
import com.tp1.dao.FacturaProductoDaoImpl;
import com.tp1.dao.ProductoDaoImpl;

public abstract class DAOFactory {

	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	
	public abstract ClienteDaoImpl getClienteDAO();
	public abstract FacturaDaoImpl getFacturaDAO();
	public abstract ProductoDaoImpl getProductoDAO();
	public abstract FacturaProductoDaoImpl getFacturaProductoDAO();
	
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
 