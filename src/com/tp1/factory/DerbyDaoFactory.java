package com.tp1.factory;

import com.tp1.connection.ConexionDerby;
import com.tp1.connection.ConexionMySql;
import com.tp1.dao.ClienteDaoImpl;
import com.tp1.dao.FacturaDaoImpl;
import com.tp1.dao.FacturaProductoDaoImpl;
import com.tp1.dao.ProductoDaoImpl;

import java.sql.Connection;

public class DerbyDaoFactory extends DAOFactory {

	ConexionDerby conexionDerby = new ConexionDerby();
	
	@Override
	public ClienteDaoImpl getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDaoImpl(conexionDerby);
	}

	@Override
	public FacturaDaoImpl getFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoDaoImpl getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaProductoDaoImpl getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
