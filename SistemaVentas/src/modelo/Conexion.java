package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	//private static final String CONTROLADOR = "";
	private static final String USER = "root";
	private static final String	PASS = "";
	private static final String URL = "jdbc:mysql://localhost:3306/sistemaventa_01?serverTimezone=UTC";
	
	
	Connection conexion;
	
	public Connection getConnection() {
		
		
		try {
			
			//Class.forName(CONTROLADOR);
			//String myBD = URL;
			conexion = DriverManager.getConnection(URL, USER, PASS);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("Falla conexión con BBDD :(");
			
		}
		
		return conexion;
		
	}

}
