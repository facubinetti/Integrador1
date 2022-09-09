package com.tp1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.tp1.idao.DAOInterface;
import com.tp1.model.Factura_Producto;

public class FacturaProductoDaoImpl implements DAOInterface<Factura_Producto> {

	Connection ctmp;
	//Constructor
	public FacturaProductoDaoImpl(Connection conexion) {
		this.ctmp = conexion;
	}
	
	public void crear() {
		try {
			String table = "CREATE TABLE factura_producto("
					+ "idFactura INT,"
					+ "idProducto INT,"
					+ "cantidad INT)";
//					+ "ALTER TABLE `factura_producto`"
//					+ "ADD KEY `idFactura` (`idFactura`,`idProducto`),"
//					+ "ADD KEY `idProducto` (`idProducto`)";
			this.ctmp.prepareStatement(table).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean crearRelacion() {
		try {
			String alter = "ALTER TABLE factura_producto "
					+ "ADD CONSTRAINT FK_factura_producto_factura "
					+ "FOREIGN KEY (idFactura) "
					+ "REFERENCES factura (id) ";
			String alter2 = "ALTER TABLE factura_producto "
					+ "ADD CONSTRAINT FK_factura_producto_producto "
					+ "FOREIGN KEY (idProducto) "
					+ "REFERENCES producto (id) ";

			this.ctmp.prepareStatement(alter).execute();
			this.ctmp.prepareStatement(alter2).execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registrarObj(Factura_Producto obj) {
		String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)";
		Boolean registrar = false;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(insert);
			
			ps.setInt(1, obj.getIdFactura());
			ps.setInt(2, obj.getIdProducto());
			ps.setInt(3, obj.getCantidad());
			
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
	public ArrayList<Factura_Producto> obtenerTodos() {
		ArrayList<Factura_Producto> listaFacturas = new ArrayList<>();
		String select = "SELECT * FROM factura_producto";
		PreparedStatement ps;
		try {
			ps = this.ctmp.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura_Producto tmp = new Factura_Producto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				listaFacturas.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaFacturas;

	}

	public ArrayList obtenerTodosAgrupadosPorId() {
		ArrayList listaFacturas = new ArrayList<>();
		String select = "SELECT idProducto, cantidad FROM factura_producto fp JOIN producto p ON fp.idProducto = p.id GROUP BY idProducto ";
		PreparedStatement ps;
		try {
			ps = this.ctmp.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura_Producto tmp = new Factura_Producto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				listaFacturas.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaFacturas;

	}

	@Override
	public Factura_Producto getById(int id) {
		String sql = "SELECT * FROM factura_producto WHERE id ="+id;
		Factura_Producto tmp = null;
		try {
			PreparedStatement ps = this.ctmp.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			tmp = new Factura_Producto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
		}
		catch (Exception e) {
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
			PreparedStatement tmp = this.ctmp.prepareStatement(update);
			
			tmp.setInt(1,obj.getCantidad());
			tmp.setInt(2,obj.getIdFactura());
			tmp.setInt(3,obj.getIdProducto());
			tmp.executeUpdate();
			actualizar = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return actualizar;

	}

	@Override
	public boolean eliminarObj(Factura_Producto obj) {
		String delete = "DELETE FROM factura_producto WHERE idFactura = (?) AND idProducto = (?)";
		Boolean eliminar = false;
		try {
			PreparedStatement tmp = this.ctmp.prepareStatement(delete);
			tmp.setInt(1, obj.getIdFactura());
			tmp.setInt(2, obj.getIdProducto());
			tmp.executeUpdate();
			eliminar = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return eliminar;

	}


	
	public boolean dropTable(){
		String dropTable = "DROP TABLE factura_producto";
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
}
