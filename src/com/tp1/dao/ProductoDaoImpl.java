package com.tp1.dao;

import java.util.ArrayList;

import com.tp1.connection.Conexion;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Producto;

public class ProductoDaoImpl implements DAOInterface<Producto>{
	Conexion ctmp;
	
	//Constructor
	public ProductoDaoImpl(Conexion conexion) {
		this.ctmp = conexion;
	}

	@Override
	public boolean registrarObj(Producto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Producto> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarObj(Producto obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarObj(Producto obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
