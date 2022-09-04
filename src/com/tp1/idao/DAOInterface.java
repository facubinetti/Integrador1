package com.tp1.idao;



import java.util.ArrayList;

public interface DAOInterface<T> {

    public boolean registrarObj ( T obj);
    public ArrayList<T> obtenerTodos();
    public T getById(int id);
    public boolean actualizarObj( T obj);
    public boolean eliminarObj ( T obj);
}
