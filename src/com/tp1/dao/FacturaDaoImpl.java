package com.tp1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tp1.connection.Conexion;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Factura;

public class FacturaDaoImpl implements DAOInterface<Factura> {
	Connection ctmp;
	
	//Constructor
	public FacturaDaoImpl(Connection conexion) {
		this.ctmp = conexion;
	}
	
	public void dropTable() throws SQLException{
		String dropTable = "DROP TABLE factura";
		try {
			this.ctmp.prepareStatement(dropTable).execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean eliminarObj(Factura factura) {
		String delete = "DELETE FROM factura WHERE id = ?";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.prepareStatement(delete);
			tmp.setInt(1,factura.getId());
			tmp.executeUpdate();
			eliminar = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminar;
	}

	@Override
	public boolean registrarObj(Factura factura) {
		String insert = "INSERT INTO factura (id, idCliente) VALUES(?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(insert);
			ps.setInt(1, factura.getId());
			ps.setInt(2, factura.getIdClient());
			ps.executeUpdate();
			registrar = true;
			ps.close();
			this.ctmp.commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return registrar;
	}

	@Override
	public ArrayList<Factura> obtenerTodos() {
		ArrayList<Factura> listaFacturas = new ArrayList<>();
		String select = "SELECT * FROM factura";
		PreparedStatement ps;
		try {
			ps = this.ctmp.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura tmp = new Factura(rs.getInt(1), rs.getInt(2));
				listaFacturas.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFacturas;
	}

	@Override
	public Factura getById(int id) {
		String sql = "SELECT * FROM factura WHERE id ="+id;
		Factura tmp =null;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tmp = new Factura(rs.getInt(1), rs.getInt(2));
			}
		}
		catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public boolean actualizarObj(Factura factura) {
		
		return false;
	}
}
