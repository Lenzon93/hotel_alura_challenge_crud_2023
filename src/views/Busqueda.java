package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.HuespedesControlador;
import controlador.ReservaControlador;
import modelo.Huespedes;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	private ReservaControlador reservaControl;
	private HuespedesControlador huespedControl;
	private ReservasView reservasVista;
	 String reserva;
	 String huespedes;
	
	 


	public Busqueda() {
		
		this.reservasVista=new ReservasView();
		this.reservaControl=new ReservaControlador();
		this.huespedControl=new HuespedesControlador();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		mostrarTablaReservas();
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		mostrarTablaHuespedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicket");
				limpiarTabla();
				if(txtBuscar.getText().equals("")) {
					System.out.println("entro en el if");
					mostrarTablaReservas();
					mostrarTablaHuespedes();
				}else {
					System.out.println("entro en el else");
					mostrarTablaReservasId();
					mostrarTablaHuespedesId();
				}

			}
		});
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int filaReservas=tbReservas.getSelectedRow();
				int filaHuespedes=tbHuespedes.getSelectedRow();
				if(filaReservas >= 0) {
					
					actualizarReservas();
					limpiarTabla();
					mostrarTablaReservas();
					mostrarTablaHuespedes();
				}else if(filaHuespedes>=0) {
					
					actualizarHuesped();
					limpiarTabla();
					mostrarTablaHuespedes();
					mostrarTablaReservasId();
					
				}
			}
		});
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				int filaHuespedes=tbHuespedes.getSelectedRow();
				

				int filaReservas=tbReservas.getSelectedRow();
				
				if(filaReservas>=0) {
					
					reserva=tbReservas.getValueAt(filaReservas,0).toString();
					int confirmar =JOptionPane.showConfirmDialog(null, "DESEA BORRAR LA RESERVA");
					if(confirmar==JOptionPane.YES_OPTION) {
						
						String valor=tbReservas.getValueAt(filaReservas, 0).toString();
						reservaControl.eliminar(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro Eliminado con exito!");
						limpiarTabla();
						mostrarTablaReservas();
						mostrarHuespedes();
						
						
					}
					else if(filaHuespedes>=0) {
						huespedes=tbHuespedes.getValueAt(filaHuespedes, 0).toString();
						int confirmaHuesped=JOptionPane.showConfirmDialog(null, "Desea borrar al huesped?");
						
						if (confirmaHuesped==JOptionPane.YES_OPTION) {
							
							String valor=tbHuespedes.getValueAt(filaHuespedes, 0).toString();
							huespedControl.eliminarHuesped(Integer.valueOf(valor));
							JOptionPane.showMessageDialog(contentPane, "Huesped eliminado con exito!"); 
							limpiarTabla();
							mostrarHuespedes();
							
							
						}
						
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el Huesped!");
					}
				}
				
			}
			
		});
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	private List<Reserva> mostrarReservas(){
		
		return this.reservaControl.mostrar();
		
	}
	
	private List<Reserva> buscarIdReservas(){
		
		return this.reservaControl.buscar(txtBuscar.getText());
		
	}
	
	
	private void mostrarTablaReservas() {
			
		List<Reserva> reserva=mostrarReservas();
		modelo.setRowCount(0);
		
		try {
			
			for (Reserva reservas:reserva) {
				
				modelo.addRow(new Object[] {
						reservas.getId(),reservas.getDataE(),reservas.getDataS(),reservas.getValor(),
						reservas.getFormaPago()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void mostrarTablaReservasId() {
		
		List<Reserva> reserva=buscarIdReservas();
	
		
		try {
			
			for (Reserva reservas:reserva) {
				
				modelo.addRow(new Object[] {
						reservas.getId(),reservas.getDataE(),reservas.getDataS(),reservas.getValor(),
						reservas.getFormaPago()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void actualizarReservas() {
		
		Optional.of(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresent(fila-> {
			LocalDate dataE;
			LocalDate dataS;
			try {
				DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
				dataE=LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString(),dateFormat);
				dataS=LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString(),dateFormat);
			}catch(Exception e) {
				throw new RuntimeException(e);
				}
			this.reservasVista.limpiarValor();
			
			String valor=calcularValorReserva(dataE,dataS);
			String formaPago=(String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
			Integer id=Integer .valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			
			if(tbReservas.getSelectedColumn()==0) {
				JOptionPane.showMessageDialog(this,"No se puede editar este campo");
				
			}else {
				this.reservaControl.actualizaReservas(dataE, dataS, valor, formaPago, id);
				JOptionPane.showMessageDialog(this, String.format("Registro Actualizado con exito"));
			}
			
			
		});
				
	}
	
	public String calcularValorReserva(LocalDate dataE,LocalDate dataS) {
		if(dataE != null && dataS != null) {
			int dias =(int) ChronoUnit.DAYS.between(dataE, dataS);
			int noche =500;
			int valor=dias*noche;
			
			return Integer.toString(valor);
			
		}else {
			
			return"";
		}
		
		
	}
	
	private void limpiarTabla() {

		
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
		
	}
	
	
     private List<Huespedes> mostrarHuespedes(){
		
		return this.huespedControl.mostrarHuesped();
	}
	private List<Huespedes> buscarHuespedesId(){
		
		return this.huespedControl.buscarHuesped(txtBuscar.getText());
	}
	
	private void mostrarTablaHuespedes() {
		
	List<Huespedes> huesped=mostrarHuespedes();
	modeloHuesped.setRowCount(0);
		
		try {
			
			for (Huespedes huespedes:huesped) {
				
				modeloHuesped.addRow(new Object[] {
						huespedes.getId(),huespedes.getNombre(),huespedes.getApellido(),huespedes.getFechanacimiento(),
						huespedes.getNacionalida(),huespedes.getTelefono(),huespedes.getIdReserva()
				});
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	private void mostrarTablaHuespedesId() {
		
		List<Huespedes> huesped=buscarHuespedesId();
		modeloHuesped.setRowCount(0);
			
			try {
				
				for (Huespedes huespedes:huesped) {
					
					modeloHuesped.addRow(new Object[] {
							huespedes.getId(),huespedes.getNombre(),huespedes.getApellido(),huespedes.getFechanacimiento(),
							huespedes.getNacionalida(),huespedes.getTelefono(),huespedes.getIdReserva()
					});
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			
		}
	
	
	private void actualizarHuesped() {
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow() , tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(filaHuesped->{
			
			String nombre= (String)modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
			String apellido= (String)modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
			Date fechaNacimiento=Date.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
			String nacionalidad= (String)modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
			String telefono= (String)modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
			Integer idReserva=Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			Integer id=Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			
			if(tbHuespedes.getSelectedColumn()==0||tbHuespedes.getSelectedColumn()==6) {
				
				JOptionPane.showMessageDialog(this,"No se puede modificar el Id!");
			}else {
				
				this.huespedControl.actualizarHuesped(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva,id);
				
				JOptionPane.showMessageDialog(this,String.format("Registro modificado con exito!"));
				
			}
			
			
			
		},()-> JOptionPane.showMessageDialog(this, "Por favor, no seas animal xd!"));
	}
	
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
