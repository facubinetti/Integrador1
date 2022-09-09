package com.tp1.controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.tp1.factory.DAOFactory;
import com.tp1.idao.DAOInterface;
import com.tp1.model.Cliente;
import com.tp1.model.Factura;
import com.tp1.model.Factura_Producto;
import com.tp1.model.Producto;
import com.tp1.vista.ViewCliente;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Controller {

	private ViewCliente vista = new ViewCliente();
	private DAOInterface<Cliente> clienteDao;
	private DAOInterface<Factura> facturaDao;
	private DAOInterface<Factura_Producto> facturaProductoDao;
	private DAOInterface<Producto> productoDao;

	
	public Controller() {
		DAOFactory mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		this.clienteDao = mysqlfactory.getClienteDAO();
		this.facturaDao = mysqlfactory.getFacturaDAO();
		this.facturaProductoDao = mysqlfactory.getFacturaProductoDAO();
		this.productoDao = mysqlfactory.getProductoDAO();
	}

	public void crearTablas(){
		this.clienteDao.crear();
		this.productoDao.crear();
		this.facturaDao.crear();
		this.facturaProductoDao.crear();
	}
	
	
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
	

	public void leerProductos() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/productos.csv"));
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void leerClientes() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/clientes.csv"));
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void leerFacturas() {
		try {
			@SuppressWarnings("deprecation")
			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/com/tp1/csv/facturas.csv"));
			for(CSVRecord row: parser) {
//			System.out.println(row.get("idProducto"));
//			System.out.println(row.get("nombre"));
//			System.out.println(row.get("valor"));

			int idFactura = parseInt(row.get("idFactura"));
			int idCliente = parseInt(row.get("idCliente"));

			Factura p = new Factura(idFactura,idCliente);
			registrar(p);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	
	
	
	
	
}
