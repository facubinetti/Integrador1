package com.tp1.model;

public class Factura {
    private int id;
    private int idClient;

    public Factura(int id, int idClient) {
        this.id = id;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
