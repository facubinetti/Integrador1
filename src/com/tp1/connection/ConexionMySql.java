package com.tp1.connection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql implements Conexion {
	
	
	private String uri = "jdbc:mysql://localhost:3306/exampleDB3";
	private Connection conn;
	
	public ConexionMySql() {
		this.memoryDriver();
		this.prenderConexion();
	}


	private void memoryDriver() {
		String driver = "com.mysql.cj.jdbc.Driver"; 
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
				this.conn = DriverManager.getConnection(this.uri, "root", "password");
				this.conn.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public Connection getConnection() {
		Connection temp = null;
		if(this.conn != null) {
			temp = this.conn;
		}
		return temp;
	}
	
}
