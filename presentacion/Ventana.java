package vc1Semana2.presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vc1Semana2.controladora.ControladoraVentana;
import vc1Semana2.logica.Empleado;
import vc1Semana2.persistencia.DAOEmpleados;

public class Ventana implements IVentana {

	private JFrame frame;
	private JTextField textCedula;
	private JTextField textApellido;
	private JTextField textNombre;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnMostrarTodo;
	private ControladoraVentana controladora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controladora = new ControladoraVentana(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textCedula = new JTextField();
		textCedula.setBounds(132, 36, 86, 20);
		frame.getContentPane().add(textCedula);
		textCedula.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(132, 114, 86, 20);
		frame.getContentPane().add(textApellido);
		textApellido.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(132, 69, 86, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					validarCedula();
					validarApellido();
					validarNombre();
					String ci = textCedula.getText();
					
					String nombre = textNombre.getText();
					String apellido = textApellido.getText();
					Empleado empleado = new Empleado(ci, nombre, apellido);
					controladora.insertarEmpleado(empleado);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
		btnAlta.setBounds(254, 35, 89, 23);
		frame.getContentPane().add(btnAlta);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validarCedula();
					validarApellido();
					validarNombre();
					String ci = textCedula.getText();
					String nombre = textNombre.getText();
					String apellido = textApellido.getText();
					Empleado empleado = new Empleado(ci, nombre, apellido);
					controladora.eliminarEmpleado(empleado);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btnEliminar.setBounds(254, 83, 89, 23);
		frame.getContentPane().add(btnEliminar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					validarCedula();
					String ci = textCedula.getText();
					controladora.buscar(ci);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btnBuscar.setBounds(254, 128, 89, 23);
		frame.getContentPane().add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					validarNombre();
					validarCedula();
					validarApellido();
					String nombre = textNombre.getText();
					String apellido = textApellido.getText();
					String ci = textCedula.getText();
					Empleado empleado = new Empleado(ci, nombre, apellido);
					controladora.update(empleado);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		btnModificar.setBounds(254, 176, 89, 23);
		frame.getContentPane().add(btnModificar);

		btnMostrarTodo = new JButton("Mostrar Todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarTodo mt = new MostrarTodo();
				mt.mostrar();
			}
		});
		btnMostrarTodo.setBounds(61, 176, 157, 23);
		frame.getContentPane().add(btnMostrarTodo);

		JLabel lblCedula = new JLabel("cedula");
		lblCedula.setBounds(48, 39, 46, 14);
		frame.getContentPane().add(lblCedula);

		JLabel lblNombre = new JLabel("nombre");
		lblNombre.setBounds(48, 72, 46, 14);
		frame.getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(48, 117, 46, 14);
		frame.getContentPane().add(lblApellido);
	}

	protected void validarCedula() throws Exception {
		try {
			if (textCedula.getText().length() == 8 || textCedula.getText().length() == 11) {
				String cedula = textCedula.getText();
				if (cedula.length() == 11) {
					cedula = cedula.replaceAll(".", cedula);
					cedula = cedula.replaceAll("-", cedula);
				}

				Integer.parseInt(cedula);

			} else {
				throw new Exception("La cedula tiene 8 o 11 digito");
			}
		} catch (NumberFormatException ex) {
			throw new Exception("No cumple el formato");
		}

	}

	protected void validarNombre() throws Exception {
		if (textNombre.getText().trim().length() > 2) {

			try {
				Integer.parseInt(textNombre.getText().trim());
				throw new Exception("El nombre no es numerico");

			} catch (Exception ex) {

			}

		} else {
			throw new Exception("El nombre es un campo obligatorio");
		}
	}

	protected void validarApellido() throws Exception {
		if (textApellido.getText().trim().length() > 2) {

			try {
				Integer.parseInt(textNombre.getText().trim());
				throw new Exception("El nombre no es numerico");

			} catch (Exception ex) {

			}

		} else {
			throw new Exception("El nombre es un campo obligatorio");
		}
	}

	@Override
	public void mensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Respuesta", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void mostrar(Empleado empleado) {
		textNombre.setText(empleado.getNombre());
		textApellido.setText(empleado.getApellido());

	}

}
