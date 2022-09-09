package com.tp1.dao.derby;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Cliente;


public class ClienteDaoImplDerby implements DAOInterface<Cliente> {
	Connection ctmp;

	public ClienteDaoImplDerby(Connection conexion) {
		this.ctmp = conexion;
	}
	@Override
	public void crear(){
		try {
			String table = "CREATE TABLE cliente("
					+ "id INT NOT NULL , "
					+ "nombre VARCHAR(500),"
					+ "email VARCHAR(200),"
					+ "PRIMARY KEY(id))";
			this.ctmp.prepareStatement(table).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean dropTable(){
		String dropTable = "DROP TABLE cliente";
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

	@Override
	public boolean registrarObj (Cliente cliente) {
		String insert = "INSERT INTO cliente (id, nombre, email) VALUES(?, ?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(insert);
			ps.setInt(1, cliente.getId());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getEmail());
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
	public ArrayList<Cliente> obtenerTodos() {
		ArrayList<Cliente> listaClientes = new ArrayList<>();
		String select = "SELECT * FROM cliente";
		PreparedStatement ps;
		try {
			ps = this.ctmp.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente tmp = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				listaClientes.add(tmp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClientes;
	}
	
	@Override
	public Cliente getById(int id) {
		String sql = "SELECT * FROM cliente WHERE id ="+id;
		Cliente tmp = null;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				tmp = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			//tmp.setId(rs.getInt(1));
			//tmp.setNombre(rs.getString(2));
			//tmp.setEmail(rs.getString(3));
		}
		catch (Exception e) {
			System.out.println("Error: Clase ClienteDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public boolean actualizarObj(Cliente cliente) {
		String update = "UPDATE cliente SET nombre=(?) , email=(?) WHERE id = (?)";
		Boolean actualizar= false;
		try {
			PreparedStatement tmp = this.ctmp.prepareStatement(update);
			tmp.setInt(1,cliente.getId());
			tmp.setString(2,cliente.getNombre());
			tmp.setString(3,cliente.getEmail());
			tmp.executeUpdate();
			actualizar = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return actualizar;
	}

	@Override
	public boolean eliminarObj(Cliente cliente) {
		String delete = "DELETE FROM cliente WHERE id = ?";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.prepareStatement(delete);
			tmp.setInt(1,cliente.getId());
			tmp.executeUpdate();
			eliminar = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return eliminar;
	}

	
	

}
