package com.tp1.idao;



import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface DAOInterface<T> {
    public void crear();

    public boolean registrarObj ( T obj);
    public ArrayList<T> obtenerTodos();
    public T getById(int id);
    public boolean actualizarObj( T obj);
    public boolean eliminarObj ( T obj);
}
