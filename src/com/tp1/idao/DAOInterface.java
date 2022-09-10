package com.tp1.idao;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
/**
 * @author Grupo 1 - Aldaya Benjamin - Binetti Facundo - Carrera Tomas - Fernandez Manuela - Rust Matias
 */
public interface DAOInterface<T> {
	
	/**
	 * Crea tabla en la base de datos
	 */
	public void crear();
	
	/**
	 * Registra una nueva instancia en la base de datos
	 * @param obj Recibe una instancia 
	 * @return booleano confirmando si registro la instancia
	 */
    public boolean registrarObj ( T obj);
    
    /**
     * Lista todas las instancias existentes en una tabla
     * @return lista de instancias
     */
    public ArrayList<T> obtenerTodos();
    
    /**
     * Obtiene instancia por id
     * @param id identificador de la instancia
     * @return instancia por id
     */
    public T getById(int id);
    
    /**
     * Actualiza instancia de la base de datos
     * @param obj Recibe una instancia  
     * @return booleano confirmando si actualizo la instancia
     */
    public boolean actualizarObj( T obj);
    
    /**
     *  Elimina una instancia de la base de datos
     * @param obj instancia
     * @return booleano confirmando si elimino la instancia
     */
    public boolean eliminarObj ( T obj);
    
    /**
     * Elimina tabla de la base de datos
     * @return  booleano confirmando si elimino la tabla 
     */
    public boolean dropTable();
    
	/**
	 * Crea relacion entre tablas
	 * @return booleano confirmando si relacionó las tablas
	 */
    public boolean crearRelacion();
}
