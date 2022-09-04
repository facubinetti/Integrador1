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
	
	
	public boolean registrar(Object obj) {
		if(obj instanceof Cliente) {
			return clienteDao.registrarObj((Cliente) obj);
		} else if (obj instanceof Factura) {
			return facturaDao.registrarObj((Factura) obj);
		} else if (obj instanceof Factura_Producto) {
			return facturaProductoDao.registrarObj((Factura_Producto) obj);
		} else if (obj instanceof Producto) {
			return productoDao.registrarObj((Producto) obj);
		}
		return false;
	}
	
	public boolean actualizar(Object obj) {
		if(obj instanceof Cliente) {
			return clienteDao.actualizarObj((Cliente) obj);
		} else if (obj instanceof Factura) {
			return facturaDao.actualizarObj((Factura) obj);
		} else if (obj instanceof Factura_Producto) {
			return facturaProductoDao.actualizarObj((Factura_Producto) obj);
		} else if (obj instanceof Producto) {
			return productoDao.actualizarObj((Producto) obj);
		}
		return false;
	}
	
	public boolean eliminar(Object obj) {
		if(obj instanceof Cliente) {
			return clienteDao.eliminarObj((Cliente) obj);
		} else if (obj instanceof Factura) {
			return facturaDao.eliminarObj((Factura) obj);
		} else if (obj instanceof Factura_Producto) {
			return facturaProductoDao.eliminarObj((Factura_Producto) obj);
		} else if (obj instanceof Producto) {
			return productoDao.eliminarObj((Producto) obj);
		}
		return false;
	}
	

	public ArrayList<?> obtener(String obj) {
		if(obj == "Cliente") {
			return clienteDao.obtenerTodos();
		} else if (obj == "Factura") {
			return facturaDao.obtenerTodos();
		} else if (obj == "Factura_Producto") {
			return facturaProductoDao.obtenerTodos();
		} else if (obj == "Producto") {
			return productoDao.obtenerTodos();
		}
		return null;
	}
	
	public void listar(String obj){
		if(obj == "Cliente") {
			vista.listarClientes(clienteDao.obtenerTodos());
		} else if (obj == "Factura") {
			vista.listarFacturas(facturaDao.obtenerTodos());
		} else if (obj == "Factura_Producto") {
			vista.listarFacturaProductos(facturaProductoDao.obtenerTodos());
		} else if (obj == "Producto") {
			vista.listarProductos(productoDao.obtenerTodos());
		}
		
	}
	

//	public void leerProductos() {
//		try {
//			@SuppressWarnings("deprecation")
//			CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("productos.csv"));
//			for(CSVRecord row: parser) {
//			System.out.println(row.get("idProducto"));
//			System.out.println(row.get("nombre"));
//			System.out.println(row.get("valor"));
//			
//			int id = row.get("idProducto");
//			String nombre = row.get("nombre");
//			float valor = row.get("valor");
//			
//			Producto p = new Producto(id,nombre,valor);
//			
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	
	
	
	
	
}
