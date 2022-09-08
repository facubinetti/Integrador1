package com.tp1.factory;

import com.tp1.connection.Conexion;
import com.tp1.connection.ConexionMySql;
import com.tp1.dao.mysql.ClienteDaoImplMySql;
import com.tp1.dao.mysql.FacturaDaoImplMySql;
import com.tp1.dao.mysql.FacturaProductoDaoImplMySql;
import com.tp1.dao.mysql.ProductoDaoImplMySql;


public class MySqlDaoFactory extends DAOFactory {

	Conexion conexion = new ConexionMySql();

	
	@Override
	public ClienteDaoImplMySql getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDaoImplMySql(conexion.getConnection());

	}
	
	@Override
	public FacturaDaoImplMySql getFacturaDAO() {
		// TODO Auto-generated method stub
		return new FacturaDaoImplMySql(conexion.getConnection());
	}
	
	@Override
	public ProductoDaoImplMySql getProductoDAO() {
		// TODO Auto-generated method stub
		return new ProductoDaoImplMySql(conexion.getConnection());
	}
	
	@Override
	public FacturaProductoDaoImplMySql getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return new FacturaProductoDaoImplMySql(conexion.getConnection());
	}

}
