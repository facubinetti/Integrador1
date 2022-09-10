package com.tp1.controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.tp1.dao.ClienteDaoImpl;
import com.tp1.dao.FacturaDaoImpl;
import com.tp1.dao.FacturaProductoDaoImpl;
import com.tp1.dao.ProductoDaoImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.tp1.factory.DAOFactory;
import com.tp1.model.Cliente;
import com.tp1.model.Factura;
import com.tp1.model.Factura_Producto;
import com.tp1.model.Producto;
import com.tp1.vista.ViewCliente;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 * @author Grupo 1 - Aldaya Benjamin - Binetti Facundo - Carrera Tomas - Fernandez Manuela - Rust Matias
 */
public class Controller {

	private ViewCliente vista = new ViewCliente();
	private ClienteDaoImpl clienteDao;
	private FacturaDaoImpl facturaDao;
	private FacturaProductoDaoImpl facturaProductoDao;
	private ProductoDaoImpl productoDao;
	
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;


	/**
	 * @param whichFactory Selector base de datos 1 MYSQL 2 Derby
	 */
	public Controller(int whichFactory) {
		DAOFactory factory = DAOFactory.getDAOFactory(whichFactory);
		this.clienteDao = factory.getClienteDAO();
		this.facturaDao = factory.getFacturaDAO();
		this.facturaProductoDao = factory.getFacturaProductoDAO();
		this.productoDao = factory.getProductoDAO();
	}
	/**
	 * Genera todas las tablas y relaciones en la base de datos
	 */
	public void crearTablas(){
			this.clienteDao.crear();
			this.productoDao.crear();
			this.facturaDao.crear();
			this.facturaProductoDao.crear();
			this.crearRelaciones();
	}

	/**
	 * Crea las Relaciones entre tablas en la base de datos
	 */
	private void crearRelaciones(){
		this.clienteDao.crearRelacion();
		this.productoDao.crearRelacion();
		this.facturaDao.crearRelacion();
		this.facturaProductoDao.crearRelacion();
	}

	/**
	 * Elimina todas las tablas de la base de datos
	 */
	public void eliminarTablas() {
		facturaProductoDao.dropTable();
		facturaDao.dropTable();
		productoDao.dropTable();
		clienteDao.dropTable();
	}

	/**
	 * Registra la instancia en la base de datos
	 * @param obj Recibe una instancia de algun modelo
	 * @return Confirma si registro la instancia
	 */
	public boolean registrar(Object obj) {
		if(obj instanceof Cliente) {
			return clienteDao.registrarObj((Cliente) obj);
		} if (obj instanceof Factura) {
			return facturaDao.registrarObj((Factura) obj);
		} if (obj instanceof Factura_Producto) {
			return facturaProductoDao.registrarObj((Factura_Producto) obj);
		} if (obj instanceof Producto) {
			return productoDao.registrarObj((Producto) obj);
		}
		return false;
		
	}

	/**
	 * Actualiza la instancia recibida en la base de datos
	 * @param obj Instancia del modelo
	 * @return Confirma la actualizacion en la base de datos
	 */
	public boolean actualizar(Object obj) {
		if(obj instanceof Cliente) {
			return clienteDao.actualizarObj((Cliente) obj);
		} if (obj instanceof Factura) {
			return facturaDao.actualizarObj((Factura) obj);
		} if (obj instanceof Factura_Producto) {
			return facturaProductoDao.actualizarObj((Factura_Producto) obj);
		} if (obj instanceof Producto) {
			return productoDao.actualizarObj((Producto) obj);
		}
		return false;
	}

	/**
	 * Elimina la instancia recibida de la base datos
	 * @param obj Instancia del modelo
	 * @return Confirma la eliminacion de la instancia en la base de datos
	 */
	public boolean eliminar(Object obj) {
		if(obj instanceof Cliente) {
			return clienteDao.eliminarObj((Cliente) obj);
		} if (obj instanceof Factura) {
			return facturaDao.eliminarObj((Factura) obj);
		} if (obj instanceof Factura_Producto) {
			return facturaProductoDao.eliminarObj((Factura_Producto) obj);
		} if (obj instanceof Producto) {
			return productoDao.eliminarObj((Producto) obj);
		}
		return false;
	}
	

	public ArrayList<?> obtener(String obj) {
		if(obj == "Cliente") {
			return clienteDao.obtenerTodos();
		} if (obj == "Factura") {
			return facturaDao.obtenerTodos();
		} if (obj == "Factura_Producto") {
			return facturaProductoDao.obtenerTodos();
		} if (obj == "Producto") {
			return productoDao.obtenerTodos();
		}
		return null;
	}

	/**
	 * Muestra/Lista todos los registros de la tabla
	 * @param obj Nombre de la tabla a mostrar
	 */
	public void listar(String obj){
		if(obj == "Cliente") {
			vista.listarClientes(clienteDao.obtenerTodos());
		} if (obj == "Factura") {
			vista.listarFacturas(facturaDao.obtenerTodos());
		} if (obj == "Factura_Producto") {
			vista.listarFacturaProductos(facturaProductoDao.obtenerTodos());
		} if (obj == "Producto") {
			vista.listarProductos(productoDao.obtenerTodos());
		}
		
	}

	/**
	 * Lee los archivos csv
	 */
	public void leerArchivos() {
		leerProductos();
		leerClientes();
		leerFacturas();
		leerFacturas_productos();
	}

	/**
	 * Lee productos.csv y lo registra en la base de datos
	 * @see Controller#registrar(Object)
	 */
	private void leerProductos() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/productos.csv"));
			System.out.println("Estoy cargando los productos...");
			for(CSVRecord row: parser) {
//			System.out.println(row.get("idProducto"));
//			System.out.println(row.get("nombre"));
//			System.out.println(row.get("valor"));

			int id = parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			float valor = parseFloat(row.get("valor"));

			Producto p = new Producto(id,nombre,valor);
			registrar(p);

			}
			System.out.println("No se me da nada mal");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lee clientes.csv y lo registra en la base de datos
	 */
	private void leerClientes() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/clientes.csv"));
			System.out.println("Ahora estoy leyendo los clientes...");
			for(CSVRecord row: parser) {
//			System.out.println(row.get("idProducto"));
//			System.out.println(row.get("nombre"));
//			System.out.println(row.get("valor"));

			int id = parseInt(row.get("idCliente"));
			String nombre = row.get("nombre");
			String email = row.get("email");

			Cliente p = new Cliente(id,nombre,email);
			registrar(p);

			}
			System.out.println("Bastante facil, quiero mas archivos para cargar");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lee facturas.csv y lo registra en la base de datos
	 */
	private void leerFacturas() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/facturas.csv"));
			System.out.println("Gracias por las facturas pero");
			for(CSVRecord row: parser) {
//			System.out.println(row.get("idProducto"));
//			System.out.println(row.get("nombre"));
//			System.out.println(row.get("valor"));

			int idFactura = parseInt(row.get("idFactura"));
			int idCliente = parseInt(row.get("idCliente"));

			Factura p = new Factura(idFactura,idCliente);
			registrar(p);

			}
			System.out.println("No te queda nada mas?");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lee facturas-productos.csv y lo registra en la base de datos
	 */
	private void leerFacturas_productos() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/facturas-productos.csv"));
			System.out.println("Gracias por las facturas pero");
			for(CSVRecord row: parser) {
//			System.out.println(row.get("idProducto"));
//			System.out.println(row.get("nombre"));
//			System.out.println(row.get("valor"));

				int idFactura = parseInt(row.get("idFactura"));
				int idProducto = parseInt(row.get("idProducto"));
				int cantidad = parseInt(row.get("cantidad"));

				Factura_Producto p = new Factura_Producto(idFactura,idProducto,cantidad);
				registrar(p);

			}
			System.out.println("No te queda nada mas?");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList obtenerFactura_ProductoAgrupadosPorId(){
		return facturaProductoDao.obtenerTodosAgrupadosPorId();
	}



	
	
	
	
	
	
	
}
