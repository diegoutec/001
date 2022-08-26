package vc1Semana2.controladora;

import vc1Semana2.logica.Empleado;
import vc1Semana2.persistencia.DAOEmpleados;
import vc1Semana2.presentacion.IVentana;

public class ControladoraVentana {

	private IVentana ventana;
	public ControladoraVentana(IVentana ventana)
	{
		this.ventana=ventana;
	}
	public void insertarEmpleado(Empleado empleado) {
		
		
		boolean sePudo=DAOEmpleados.insert(empleado);
		if(sePudo)
			ventana.mensaje("Se pudo insertar");
		else
			ventana.mensaje("Error, al insertar");
	}
	public void eliminarEmpleado(Empleado empleado) {
		boolean sePudo=DAOEmpleados.delete(empleado);
		if(sePudo)
			ventana.mensaje("Se pudo eliminar");
		else
			ventana.mensaje("Error, al eliminar");
	}
	public void buscar(String ci) {
		Empleado empleado=DAOEmpleados.find(ci);
		if(empleado==null)
			ventana.mensaje("No existe el empleado con ese documento");
		else 
			ventana.mostrar(empleado);
		
	}
	public void update(Empleado empleado) {
		boolean sePudo=DAOEmpleados.update(empleado);
		if(sePudo)
			ventana.mensaje("Se pudo modificar");
		else
			ventana.mensaje("Error, al modificar");
	}

}
