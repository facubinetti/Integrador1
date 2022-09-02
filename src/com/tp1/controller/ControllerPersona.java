package com.tp1.controller;

import java.util.ArrayList;


import com.tp1.factory.DAOFactory;
import com.tp1.idao.IPersonaDao;
import com.tp1.model.Persona;
import com.tp1.vista.ViewPersona;

public class ControllerPersona {

	private ViewPersona vista = new ViewPersona();
	private IPersonaDao personaDao;
	
	public ControllerPersona() {
		DAOFactory mysqlfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
		this.personaDao = mysqlfactory.getCustomerDAO();
	}
	
	public boolean registrar(Persona persona) {
		return personaDao.registrar(persona);
	}
	
	public boolean actualizar(Persona persona) {
		return personaDao.actualizarPersona(persona);
	}
	
	public void eliminar(Persona persona) {
		personaDao.eliminarPersona(persona);
	}
	
	public void listarPersonas(){
		vista.listarPersonas(personaDao.obtenerPersonas());
	}
	
	public ArrayList<Persona> obtenerPersonas(){
		return personaDao.obtenerPersonas();
	}
	
	
	
	
	
	
}
