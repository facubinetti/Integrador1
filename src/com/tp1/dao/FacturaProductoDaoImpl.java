package com.tp1.dao;

import java.util.ArrayList;

import com.tp1.connection.Conexion;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Factura_Producto;

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
	
	public void crear() throws SQLException{
		try {
			String table = "CREATE TABLE IF NOT EXISTS factura_producto("
					+ "idFactura INT,"
					+ "idProducto INT,"
					+ "cantidad INT)"
					+ "ALTER TABLE `factura_producto`"
					+ "ADD KEY `idFactura` (`idFactura`,`idProducto`),"
					+ "ADD KEY `idProducto` (`idProducto`)";
			this.ctmp.getConnection().prepareStatement(table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public boolean registrarObj(Factura_Producto obj) {
		String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.getConnection().prepareStatement(insert);
			
			ps.setInt(1, Factura_Producto.getIdFactura());
			ps.setInt(2, Factura_Producto.getIdProducto());
			ps.setInt(3, Factura_Producto.getCantidad());
			
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
	public ArrayList<Factura_Producto> obtenerTodos() {
		ArrayList<Factura> listaFacturas = new ArrayList<>();
		String select = "SELECT * FROM factura_producto";
		PreparedStatement ps;
		try {
			ps = this.ctmp.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura tmp = new Factura_Producto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				listaFacturas.add(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFacturas;

	}

	@Override
	public Factura_Producto getById(int id) {
		String sql = "SELECT * FROM factura_producto WHERE id ="+id;
		Factura tmp =null;
		try {
			PreparedStatement ps = this.ctmp.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			tmp = new Factura_Producto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
		}
		catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;

	}

	@Override
	public boolean actualizarObj(Factura_Producto obj) {
		String update = "UPDATE factura_producto SET cantidad=(?) WHERE idFactura = (?) AND idProducto = (?)";
		Boolean actualizar= false;
		try {
			PreparedStatement tmp = this.ctmp.getConnection().prepareStatement(update);
			
			tmp.setInt(1,Factura_Producto.getCantidad());
			tmp.setInt(2,Factura_Producto.getIdFactura());
			tmp.setInt(3,Factura_Producto.getIdProducto());
			tmp.executeUpdate();
			actualizar = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return actualizar;

	}

	@Override
	public boolean eliminarObj(Factura_Producto obj) {
		String delete = "DELETE FROM factura_producto WHERE id = ?";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.getConnection().prepareStatement(delete);
			tmp.setInt(1,Factura_Producto.getId());
			tmp.executeUpdate();
			eliminar = true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return eliminar;

	}

}