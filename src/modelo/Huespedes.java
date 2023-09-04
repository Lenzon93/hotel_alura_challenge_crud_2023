package modelo;

import java.time.LocalDate;

public class Huespedes {

	public Huespedes(Integer id, String nombre, String apellido, LocalDate fechanacimiento, String nacionalida,
			String telefono, Integer idReserva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechanacimiento;
		this.nacionalida = nacionalida;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	private Integer id;
	private String nombre;
	private String apellido;
	private LocalDate fechanacimiento;
	private String nacionalida;
	private String telefono;
	private Integer idReserva;
	
	
	public Huespedes( String nombre, String apellido, LocalDate fechanacimiento, String nacionalida,
			String telefono, Integer idReserva) {
	
	
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechanacimiento = fechanacimiento;
		this.nacionalida = nacionalida;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(LocalDate fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getNacionalida() {
		return nacionalida;
	}
	public void setNacionalida(String nacionalida) {
		this.nacionalida = nacionalida;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	
	
	
	
	
}
