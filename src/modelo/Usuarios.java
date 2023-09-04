package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConexionBase;

public class Usuarios {

	private String contraseña;
	private String nombre;
	
	
public Usuarios(String nombre,String contraseña) {
		
		this.nombre=nombre;
		this.contraseña=contraseña;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
	public static boolean validarUsuario(String nombre,String Contraseña) {
		
		ConexionBase con=new ConexionBase();
		Connection conexion=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		
		try {
			conexion=con.conectarBase();
			System.out.println("entro en la conecion");
			ps=conexion.prepareStatement("SELECT * FROM usuarios WHERE nombre=? AND contraseña=?");
			ps.setString(1, nombre);
	
			ps.setString(2, Contraseña);
			rs=ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}finally {
			try {	
				if(rs != null) {
					
					rs.close();
				}
				if(ps != null) {
					
					ps.close();
				}if(conexion != null) {
					
					conexion.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		
		
	}
	
}
