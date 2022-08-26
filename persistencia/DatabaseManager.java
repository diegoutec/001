package vc1Semana2.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DatabaseManager {
	
	private static Connection databaseConnection;
	
	private static String Connection_String = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String usuario = "C##patrones";
	private static String contraseña = "SYSTEM";
	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	static{
		try {
			Locale.setDefault(new Locale("es","ES"));
			Class.forName(DRIVER);
			databaseConnection = DriverManager.getConnection(Connection_String, usuario, contraseña);
			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error al conectarse a la Base de datos");
			System.out.println(e.getMessage());
		}
	}
	
	private DatabaseManager()
	{
		
	}
	public static Connection getConnection(){
		return databaseConnection;
	}

}
