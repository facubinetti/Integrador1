package com.tp1.factory;

import com.tp1.connection.Conexion;
import com.tp1.connection.ConexionDerby;
import com.tp1.dao.derby.ClienteDaoImplDerby;
import com.tp1.dao.derby.FacturaDaoImplDerby;
import com.tp1.dao.derby.FacturaProductoDaoImplDerby;
import com.tp1.dao.derby.ProductoDaoImplDerby;


public class DerbyDaoFactory extends DAOFactory {

	Conexion conexion = new ConexionDerby();

	@Override
	public ClienteDaoImplDerby getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDaoImplDerby(conexion.getConnection());
	}

	@Override
	public FacturaDaoImplDerby getFacturaDAO() {
		// TODO Auto-generated method stub
		return new FacturaDaoImplDerby(conexion.getConnection());
	}

	@Override
	public ProductoDaoImplDerby getProductoDAO() {
		// TODO Auto-generated method stub
		return new ProductoDaoImplDerby(conexion.getConnection());
	}

	@Override
	public FacturaProductoDaoImplDerby getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return new FacturaProductoDaoImplDerby(conexion.getConnection());
	}

}
