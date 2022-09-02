package com.tp1.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tp1.connection.Conexion;
import com.tp1.idao.DAOInterface;
import com.tp1.idao.IClienteDao;
import com.tp1.model.Cliente;

public class ClienteDaoImplMySql implements DAOInterface<Cliente> {
	Conexion ctmp;
	
	public ClienteDaoImplMySql(Conexion conexion) {
		this.ctmp = conexion;
	}
	
	public void crear() throws SQLException{
		try {
			String table = "CREATE TABLE IF NOT EXISTS cliente("
					+ "id INT NULL AUTO_INCREMENT, " 
					+ "nombre VARCHAR(500),"
					+ "edad INT,"
					+ "PRIMARY KEY(id))";
			this.ctmp.getConnection().prepareStatement(table).execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dropTable() throws SQLException{
		String dropTable = "DROP TABLE cliente";
		try {
			this.ctmp.getConnection().prepareStatement(dropTable).execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean registrarObj (Cliente cliente) {
		String insert = "INSERT INTO cliente (nombre, edad, id) VALUES(?, ?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.getConnection().prepareStatement(insert);
			ps.setString(1, cliente.getNombre());
			ps.setInt(2, cliente.getEdad());
			ps.setInt(3, cliente.getId());
			ps.executeUpdate();
			registrar = true;
			ps.close();
			this.ctmp.getConnection().commit();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return registrar;
	}

	@Override
	public ArrayList<Cliente> obtenerTodos() {
		ArrayList<Cliente> listaClientes = new ArrayList<>();
		String select = "SELECT * FROM cliente";
		PreparedStatement ps;
		try {
			ps = this.ctmp.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente tmp = new Cliente(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaClientes.add(tmp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClientes;
	}

	@Override
	public Cliente getById(int id) {
		Cliente tmp;
		String sql = "SELECT * FROM cliente WHERE id ="+id;
		try {
			PreparedStatement ps = this.ctmp.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			tmp.setId(rs.getInt(1));
			tmp.setNombre(rs.getString(2));
			tmp.setEdad(rs.getInt(3));
		}
		catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public boolean actualizarObj(Cliente cliente) {
//		 intente hacer un update y tira error, y cuando logre que no tire error no lo actualizaba, me canse y lo solucione dropeando a la cliente y volviendola a agregar
		if(eliminarCliente(cliente)&&registrar(cliente))
			return true;
		else
			return false;
	}

	@Override
	public boolean eliminarObj(Cliente cliente) {
		String delete = "DELETE FROM cliente WHERE id = ?";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.getConnection().prepareStatement(delete);
			tmp.setInt(1,cliente.getId());
			tmp.executeUpdate();
			eliminar = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminar;
	}

}
