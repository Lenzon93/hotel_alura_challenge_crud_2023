package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
	
	public static void main(String[] args) throws SQLException {
		ConexionBase con=new ConexionBase();
		Connection conexion=con.conectarBase();
		
		System.out.println("conecto bien");
		
		conexion.close();
		
		System.out.println("cerro bien la conexion");
	}

}
