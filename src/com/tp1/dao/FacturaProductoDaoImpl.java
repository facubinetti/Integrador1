package com.tp1.dao;

import java.util.ArrayList;

import com.tp1.connection.Conexion;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Factura_Producto;

public class FacturaProductoDaoImpl implements DAOInterface<Factura_Producto> {
	Conexion ctmp;
	//Constructor
		public FacturaProductoDaoImpl(Conexion conexion) {
			this.ctmp = conexion;
		}

	@Override
	public boolean registrarObj(Factura_Producto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Factura_Producto> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura_Producto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarObj(Factura_Producto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarObj(Factura_Producto obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
