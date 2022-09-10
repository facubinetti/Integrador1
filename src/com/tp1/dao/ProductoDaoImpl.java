package com.tp1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.tp1.idao.DAOInterface;
import com.tp1.model.Producto;

public class ProductoDaoImpl implements DAOInterface<Producto> {
	
	Connection ctmp;
	
	//Constructor
	public ProductoDaoImpl(Connection conexion) {
		this.ctmp = conexion;
	}
	@Override
	public void crear() {
		try {
			String table = "CREATE TABLE producto("
					+ "id INT NOT NULL , "
					+ "nombre VARCHAR(45),"
					+ "valor FLOAT,"
					+ "PRIMARY KEY(id))";
			this.ctmp.prepareStatement(table).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean registrarObj(Producto obj) {
		String insert = "INSERT INTO producto (id, nombre, valor) VALUES(?, ?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(insert);
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getNombre());
			ps.setFloat(3, obj.getValor());
			ps.executeUpdate();
			registrar = true;
			ps.close();
			this.ctmp.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return registrar;
	}

	@Override
	public ArrayList<Producto> obtenerTodos() {
		ArrayList<Producto> listaProductos = new ArrayList<>();
		String select = "SELECT * FROM producto";
		PreparedStatement ps;
		try {
			ps = this.ctmp.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Producto tmp = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
				listaProductos.add(tmp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProductos;
	}

	@Override
	public Producto getById(int id) {
		String sql = "SELECT * FROM producto WHERE id = ?";
		Producto tmp = null;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				tmp = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
			}
			

			//tmp.setId(rs.getInt(1));
			//tmp.setNombre(rs.getString(2));
			//tmp.setValor(rs.getString(3));
		}
		catch (Exception e) {
			System.out.println("Error: Clase ProductoDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public boolean actualizarObj(Producto obj) {

		String update = "UPDATE producto SET nombre=(?) , valor=(?) WHERE id = (?)";

		Boolean actualizar= false;
		try {
			PreparedStatement tmp = this.ctmp.prepareStatement(update);

			tmp.setInt(1,obj.getId());
			tmp.setString(2,obj.getNombre());
			tmp.setFloat(3,obj.getValor());

			tmp.executeUpdate();
			actualizar = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return actualizar;
	}

	@Override
	public boolean eliminarObj(Producto obj) {
		String delete = "DELETE FROM producto WHERE id = ?";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.prepareStatement(delete);

			tmp.setInt(1,obj.getId());

			tmp.executeUpdate();
			eliminar = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return eliminar;
	}

	
	public boolean dropTable(){
		String dropTable = "DROP TABLE producto";
		boolean drop = false;
		try {
			this.ctmp.prepareStatement(dropTable).execute();
			drop = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return drop;
	}

	@Override
	public boolean crearRelacion() {
		return false;
	}

}
