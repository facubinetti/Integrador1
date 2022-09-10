package com.tp1.demo;


import com.tp1.controller.Controller;

public class Demo {
	public static void main (String[] args) {

//		Controller cp = new Controller(Controller.MYSQL_JDBC);		
		Controller cp = new Controller(Controller.DERBY_JDBC);

//		cp.crearTablas();
//		cp.leerArchivos();
//		cp.listar("Producto");
		//cp.listar("Cliente");
//		cp.listar("Factura");
//		cp.listar("Factura_Producto");

//		cp.listarProducto(cp.obtenerProductodeMayorRecaudacion());

		cp.listarClientesMayorFacturados();

//		cp.eliminarTablas();
	}
}
