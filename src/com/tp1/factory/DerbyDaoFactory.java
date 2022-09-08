package com.tp1.factory;

import com.tp1.connection.ConexionDerby;
import com.tp1.dao.ClienteDaoImplMySql;
import com.tp1.dao.FacturaDaoImplMySql;
import com.tp1.dao.FacturaProductoDaoImplMySql;
import com.tp1.dao.ProductoDaoImplMySql;


public class DerbyDaoFactory extends DAOFactory {

	ConexionDerby conexionDerby = new ConexionDerby();
	
	@Override
	public ClienteDaoImplMySql getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDaoImplMySql(conexionDerby);
	}

	@Override
	public FacturaDaoImplMySql getFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoDaoImplMySql getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaProductoDaoImplMySql getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
