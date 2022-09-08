package com.tp1.factory;

import com.tp1.connection.ConexionMySql;
import com.tp1.dao.ClienteDaoImplMySql;
import com.tp1.dao.FacturaDaoImplMySql;
import com.tp1.dao.FacturaProductoDaoImplMySql;
import com.tp1.dao.ProductoDaoImplMySql;

public class MySqlDaoFactory extends DAOFactory {

	ConexionMySql conexionMySql = new ConexionMySql();
	
	@Override
	public ClienteDaoImplMySql getClienteDAO() {
		// TODO Auto-generated method stub
		return new ClienteDaoImplMySql(conexionMySql);
	}
	
	@Override
	public FacturaDaoImplMySql getFacturaDAO() {
		// TODO Auto-generated method stub
		return new FacturaDaoImplMySql(conexionMySql);
	}
	
	@Override
	public ProductoDaoImplMySql getProductoDAO() {
		// TODO Auto-generated method stub
		return new ProductoDaoImplMySql(conexionMySql);
	}
	
	@Override
	public FacturaProductoDaoImplMySql getFacturaProductoDAO() {
		// TODO Auto-generated method stub
		return new FacturaProductoDaoImplMySql(conexionMySql);
	}

}
