package com.tp1.factory;

import com.tp1.idao.DAOInterface;

public abstract class DAOFactory<T> {

	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	
	public abstract DAOInterface getClienteDAO();
	public abstract DAOInterface getFacturaDAO();
	public abstract DAOInterface getProductoDAO();
	public abstract DAOInterface getFacturaProductoDAO();
	
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
 