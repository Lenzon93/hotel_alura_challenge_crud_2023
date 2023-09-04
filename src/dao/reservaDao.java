package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class reservaDao {
	private Connection con;

	public reservaDao(Connection con) {

		this.con = con;
	}
	
	
	public void guardar(Reserva reserva) {
		 try {
			 
			 String sql="INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_de_pago)"
			 		+ "VALUES(?,?,?,?)";
			try(PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){ 
				
				ps.setObject(1, reserva.getDataE());
				ps.setObject(2, reserva.getDataS());
				ps.setObject(3, reserva.getValor());
				ps.setObject(4, reserva.getFormaPago());
				ps.executeUpdate();
				
				try(ResultSet rs=ps.getGeneratedKeys()){
					while(rs.next()) {
						
						reserva.setId(rs.getInt(1));
						
						
					}
				}
				
			}
		} catch (Exception e) {
			throw new RuntimeException("animal"+e.getMessage(),e);
		}
		
	}
	
	public List mostrar(){
		
		List<Reserva> reservas=new ArrayList<Reserva>();
		
		try {
			String sql="SELECT id,fecha_entrada,fecha_salida,valor, forma_de_pago FROM reservas";
			
			try(PreparedStatement ps=con.prepareStatement(sql)){
				
				ps.execute();
				
				tranformarResultado(reservas,ps);
			}
			
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
		
		
		
	}
	
	public void actualizar(LocalDate dataE,LocalDate dataS,String valor,String formaPago,Integer id) {
		
		try(PreparedStatement ps= con.prepareStatement("UPDATE reservas SET "
				+ " fecha_entrada=?,fecha_salida=?,valor=?,forma_de_pago=? WHERE id=?")){
			
			ps.setObject(1, dataE);
			ps.setObject(2, dataS);
			ps.setObject(3,valor);
			ps.setObject(4, formaPago);
			ps.setObject(5, id);
			ps.execute();
			
		}catch(SQLException e) {
			
			throw new RuntimeException("animal "+e.getMessage(),e);
		}
		
	}


	private void tranformarResultado(List<Reserva> reservas,PreparedStatement ps) throws SQLException {
		
		try(ResultSet rs=ps.getResultSet()){
			
			while(rs.next()) {
				
				int id=rs.getInt("id");
				LocalDate fechaE=rs.getDate("fecha_entrada").toLocalDate().plusDays(1);
				LocalDate fechaS=rs.getDate("fecha_salida").toLocalDate().plusDays(1);
				String valor=rs.getString("valor");
				String formaPago=rs.getString("forma_de_pago");
				
				Reserva producto=new Reserva(id,fechaE, fechaS, valor, formaPago);
				reservas.add(producto);
				
			}
		}
	}
	public List<Reserva> buscraId(String id){
		
		List<Reserva> reservas=new ArrayList<Reserva>();
		
		try {
			String sql="SELECT id,fecha_entrada,fecha_salida,valor, forma_de_pago FROM reservas WHERE id=?";
			
			try(PreparedStatement ps=con.prepareStatement(sql)){
				ps.setString(1, id);
				ps.execute();
				
				tranformarResultado(reservas,ps);
			}
			
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
		
		
	}
	
	public void eliminar(Integer id) {
		String sql="DELETE FROM	reservas WHERE id=?";
		try {
				Statement state=con.createStatement();
				state.execute("SET FOREIGN_KEY_CHECKS=0");
				
				PreparedStatement ps=con.prepareStatement(sql);
						
			
			ps.setInt(1, id);
			ps.execute();		
			state.execute("SET FOREIGN_KEY_CHECKS=1");
			
			
		}catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		
	}

}
