package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionBase {

	public DataSource dataSource;

	public ConexionBase() {
		String sql="jdbc:mysql://localhost/hotel_alura_hn";
		ComboPooledDataSource comboPool=new ComboPooledDataSource();
		comboPool.setJdbcUrl(sql);
		comboPool.setUser("root");
		comboPool.setPassword("Lenzon941124");
		this.dataSource=comboPool;
	}
	
	public Connection conectarBase() {
		try {
			return this.dataSource.getConnection();
			
		}catch(SQLException e) {
			System.out.println("hubo un error de conexion");
			throw new RuntimeException(e);
			
		}
		
	}
}
