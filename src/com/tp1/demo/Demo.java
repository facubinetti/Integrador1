package com.tp1.demo;


import com.tp1.controller.Controller;

public class Demo {
	public static void main (String[] args) {

		Controller cp = new Controller();
//		cp.crearTablas();
//		cp.leerProductos();
//		cp.listar("Producto");
//		cp.leerClientes();
//      cp.listar("Cliente");
//		cp.leerFacturas();
		cp.listar("Factura");
	}
}
