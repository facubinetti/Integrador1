package com.tp1.idao;

import com.tp1.model.Cliente;

import java.util.ArrayList;

public interface DAOInterface<T> {

    public boolean registrarObj ( T obj);
    public ArrayList<T> obtenerTodos();
    public Cliente getById(int id);
    public boolean actualizarObj( T obj);
    public boolean eliminarObj ( T obj);

    boolean actualizarObj(Cliente cliente);

    boolean eliminarObj(Cliente cliente);
}
