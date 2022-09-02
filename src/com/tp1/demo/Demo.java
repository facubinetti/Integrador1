package com.tp1.demo;

import java.util.ArrayList;


import com.tp1.controller.ControllerPersona;
import com.tp1.model.Persona;

public class Demo {
	public static void main (String[] args) {
		ControllerPersona cp = new ControllerPersona();
		
//		ArrayList<Persona> personas = cp.obtenerPersonas();
//		
//		for(Persona p: personas) {
//
//			if(p.getEdad() == 21) {
//				System.out.println(p);
//			}
//				
//
//		}
		
		
		cp.listarPersonas();
//		
//		Persona persona1 = personas.get(4);
//		
//		persona1.setEdad(32);
//		persona1.setNombre("Nelson");
//		
//		System.out.println("---");
//		
//		
//		
//		System.out.println(cp.actualizar(persona1));
//		
//		cp.listarPersonas();
		
	}
}
