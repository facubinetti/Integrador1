package com.tp1.idao;

import java.util.ArrayList;


import com.tp1.model.Persona;



public interface IPersonaDao {

	public boolean registrar(Persona persona);
	public ArrayList<Persona> obtenerPersonas();
	public Persona obtenerPersona(int id);
	public boolean actualizarPersona(Persona persona);
	public boolean eliminarPersona(Persona persona);
	
	
}
