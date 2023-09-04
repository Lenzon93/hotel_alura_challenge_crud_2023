package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;
import modelo.Reserva;

public class HuespedesDao {
	
	private Connection con;

	public HuespedesDao(Connection con) {
	
		this.con = con;
	}
	
	
	public void guardar(Huespedes huespedes) {
		
		try {
			
			String sql="INSERT INTO huespedes(nombre,apellido,fecha_nacimiento,nacionalidad,telefono,id_reserva) "
					+ "VALUES(?,?,?,?,?,?)";
			try(PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
				
				ps.setString(1, huespedes.getNombre());
				ps.setString(2, huespedes.getApellido());
				ps.setObject(3, huespedes.getFechanacimiento());
				ps.setString(4, huespedes.getNacionalida());
				ps.setString(5, huespedes.getTelefono());
				ps.setInt(6, huespedes.getIdReserva());
				
				ps.execute();
				try(ResultSet rs=ps.getGeneratedKeys()){
					
					while(rs.next()) {
						
						huespedes.setId(rs.getInt(1));
					}
				}
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException("animal"+e.getMessage(),e);
		}
	}
	

	  	public List<Huespedes> mostrar(){
		
		List<Huespedes> huespedes=new ArrayList<>();
		
		try {
			String sql="SELECT id,nombre,apellido,fecha_nacimiento, nacionalidad,telefono,id_reserva FROM huespedes";
			
			try(PreparedStatement ps=con.prepareStatement(sql)){
				
				ps.execute();
				
				tranformarResultado(huespedes,ps);
			}
			
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
		
		
		
	}
	  	
		public List<Huespedes> buscarId(String id){
			
			List<Huespedes> huespedes=new ArrayList<>();
			
			try {
				String sql="SELECT id,nombre,apellido,fecha_nacimiento, nacionalidad,telefono,id_reserva FROM huespedes "
						+ "WHERE id=?";
				
				try(PreparedStatement ps=con.prepareStatement(sql)){
					ps.setString(1	,id);
					
					ps.execute();
					
					tranformarResultado(huespedes,ps);
				}
				
				return huespedes;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			
			
			
			
			
		}
		
		
		public void Actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Integer id_reserva,Integer id) {
			
		
			try {
				
				Statement state=con.createStatement();
				state.execute("SET FOREIGN_KEY_CHECKS=0");
				
				
				PreparedStatement stm = con.prepareStatement("UPDATE huespedes SET nombre=? , apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=?, id_reserva=? WHERE  id= ?"); 
			
				stm.setString(1, nombre);
				stm.setString(2, apellido);
				stm.setDate(3, fechaNacimiento);
				stm.setString(4, nacionalidad);
				stm.setString(5, telefono);
				stm.setInt(6, id_reserva);
				stm.setInt(7, id);
				stm.execute();
				state.execute("SET FOREIGN_KEY_CHECKS=1");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		
		public void eliminar(Integer id) {
			
			try(PreparedStatement ps=con.prepareStatement("DELETE FRON huespedes WHERE id=?")){
				ps.setObject(1, id);
				ps.execute();
				
				
			}catch (SQLException e) {
				
				throw new RuntimeException(e);
			}
			
		}
	  	private void tranformarResultado(List<Huespedes> huespedes,PreparedStatement ps) throws SQLException {
			
			try(ResultSet rs=ps.executeQuery()){
				
				while(rs.next()) {
					
					int id=rs.getInt("id");
					
					String nombre=rs.getString("nombre");
					String apellido=rs.getString("apellido");
					LocalDate fechaNacimiento=rs.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
					String nacionalidad=rs.getString("nacionalidad");
					String telefono=rs.getString("telefono");
					int idReserva=rs.getInt("telefono");
					
					Huespedes hueped=new Huespedes(id,nombre, apellido, fechaNacimiento, nacionalidad, telefono,idReserva);
					
					huespedes.add(hueped);
					
				}
			}
		}
	

}
