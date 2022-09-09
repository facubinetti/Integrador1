package com.tp1.factory;

import com.tp1.connection.Conexion;
import com.tp1.connection.ConexionMySql;
import com.tp1.dao.ClienteDaoImpl;
import com.tp1.dao.FacturaDaoImpl;
import com.tp1.dao.FacturaProductoDaoImpl;
import com.tp1.dao.ProductoDaoImpl;


public class MySqlDaoFactory extends DAOFactory {

	Conexion conexion = new ConexionMySql();

	
	@Override
	public ClienteDaoImpl getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDaoImpl(conexion.getConnection());

	}
	
	@Override
	public FacturaDaoImpl getFacturaDAO() {
		// TODO Auto-generated method stub
		return new FacturaDaoImpl(conexion.getConnection());
	}
	
	@Override
	public ProductoDaoImpl getProductoDAO() {
		// TODO Auto-generated method stub
		return new ProductoDaoImpl(conexion.getConnection());
	}
	
	@Override
	public FacturaProductoDaoImpl getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return new FacturaProductoDaoImpl(conexion.getConnection());
	}

}
