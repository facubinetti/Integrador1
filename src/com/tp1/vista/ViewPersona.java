package com.tp1.vista;

import java.util.List;

import com.tp1.model.Persona;

public class ViewPersona {

	public void verPersona(Persona p) {
		System.out.println(p);
	}
	
	public void listarPersonas(List<Persona> personas) {
		for(Persona p: personas) {
			System.out.println(p);
		}
	}
}
