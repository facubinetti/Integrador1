package com.tp1.connection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDerby implements Conexion{

    private String uri = "jdbc:derby:exampleDB;create=true";
	private Connection conn;
	
	public ConexionDerby() {
		this.memoryDriver();
		this.prenderConexion();
		this.cerrarConexion();
	}


	private void memoryDriver() {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private Connection prenderConexion() {
		if(this.conn == null) {
			try {
				this.conn = DriverManager.getConnection(this.uri);
				this.conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	
	private void cerrarConexion() {
		if(this.conn != null) {
			try {
				this.conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		Connection temp = null;
		if(this.conn != null) {
			temp = this.conn;
		}
		return temp;
	}
}
