package com.tp1.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Cliente;


public class ClienteDaoImpl implements DAOInterface<Cliente> {
	Connection ctmp;

	//constructor
	public ClienteDaoImpl(Connection conexion) {
		this.ctmp = conexion;
	}
	
	/**
	 * @see DAOInterface#crear()
	 */
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
	
	/**
	 * @see DAOInterface#dropTable()
	 */
	@Override
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
	
	/**
	 * @see DAOInterface#crearRelacion()
	 */
	@Override
	public boolean crearRelacion() {
		return false;
	}

	/**
	 * @see DAOInterface#registrarObj(Object)
	 */
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
	
	/**
	 * @see DAOInterface#obtenerTodos()
	 */
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
			e.printStackTrace();
		}
		return listaClientes;
	}
	
	/**
	 * @see DAOInterface#getById(int)
	 */
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
		}
		catch (Exception e) {
			System.out.println("Error: Clase ClienteDaoImple, metodo obtener por id");
			e.printStackTrace();
		}
		return tmp;
	}

	/**
	 * @see DAOInterface#actualizarObj(Object)
	 */
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

	/**
	 * @see DAOInterface#eliminarObj(Object)
	 */
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
	
	/**
	 * Obtiene los clientes segun valor de facturacion
	 * @return lista de clientes ordenado descendientemente segun valor de facturacion
	 */
	public ArrayList<Cliente> obtenerClientesMayorFacturado() {
		ArrayList<Cliente> listaClientes = new ArrayList<>();
		String select = "select cl.clienteID,cl.nombre,cl.email,SUM(VALOR) valortotal "
				+ "from (select C.ID clienteID,C.NOMBRE nombre,C.EMAIL email,SUM(FP.CANTIDAD)*p.valor as valor "
				+ "from FACTURA_PRODUCTO fp "
				+ "join PRODUCTO P on P.ID = fp.IDPRODUCTO "
				+ "join FACTURA F on F.ID = fp.IDFACTURA "
				+ "join CLIENTE C on C.ID = F.IDCLIENTE "
				+ "group by C.ID, C.NOMBRE, C.EMAIL,p.VALOR) cl "
				+ "join CLIENTE C on cl.clienteID = C.ID "
				+ "group by cl.clienteID, cl.nombre, cl.email "
				+ "order by valortotal DESC";
		PreparedStatement ps;
		try {
			ps = this.ctmp.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente tmp = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				listaClientes.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}
	
}
