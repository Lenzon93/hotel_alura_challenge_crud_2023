package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.HuespedesDao;
import factory.ConexionBase;
import modelo.Huespedes;

public class HuespedesControlador {

	private HuespedesDao huespedesDao;

	public HuespedesControlador() {

		Connection con = new ConexionBase().conectarBase();
		this.huespedesDao = new HuespedesDao(con);

	}

	public void guardar(Huespedes huespedes) {

		this.huespedesDao.guardar(huespedes);
	}

	public List<Huespedes> mostrarHuesped() {

		return this.huespedesDao.mostrar();
	}

	public List<Huespedes> buscarHuesped(String id) {

		return this.huespedesDao.buscarId(id);
	}

	public void actualizarHuesped(String nombre, String apellido, Date fechanacimiento, String nacionalida,
			String telefono, Integer idReserva, Integer id) {
		this.huespedesDao.Actualizar(nombre, apellido, fechanacimiento, nacionalida, telefono, idReserva, id);

	}

	public void eliminarHuesped(Integer idReserva) {
		this.huespedesDao.eliminar(idReserva);
	}
}
