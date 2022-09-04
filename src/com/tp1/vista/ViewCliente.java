package com.tp1.vista;

import java.util.List;

import com.tp1.model.Cliente;
import com.tp1.model.Factura;
import com.tp1.model.Factura_Producto;
import com.tp1.model.Producto;



public class ViewCliente {

	
	public void listarClientes(List<Cliente> clientes) {
		for(Cliente p: clientes) {
			System.out.println(p);
		}
	}
	
	public void listarProductos(List<Producto> productos) {
		for(Producto p: productos) {
			System.out.println(p);
		}
	}
	
	public void listarFacturas(List<Factura> facturas) {
		for(Factura p: facturas) {
			System.out.println(p);
		}
	}
	
	public void listarFacturaProductos(List<Factura_Producto> facturasProductos) {
		for(Factura_Producto p: facturasProductos) {
			System.out.println(p);
		}
	}
	
	
}
