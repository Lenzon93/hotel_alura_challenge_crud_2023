package controlador;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import dao.reservaDao;
import factory.ConexionBase;
import modelo.Reserva;

public class ReservaControlador {
	private reservaDao reservaD;

	public ReservaControlador() {
	
		Connection con=new ConexionBase().conectarBase();
		this.reservaD=new reservaDao(con);
	}
	
	public void guardar(Reserva reserva) {
		this.reservaD.guardar(reserva);
	}
	
	public List<Reserva> mostrar(){
		
		return this.reservaD.mostrar();
	}
	
public List<Reserva> buscar(String id){
		
		return this.reservaD.buscraId(id);
	}



   public void actualizaReservas(LocalDate dataE,LocalDate dataS,String valor,String formaPago,Integer id) {
	
	this.reservaD.actualizar(dataE, dataS, valor, formaPago, id);
  }
	
   public void eliminar(Integer id) {
	   this.reservaD.eliminar(id);
	   
	   
   }

}
