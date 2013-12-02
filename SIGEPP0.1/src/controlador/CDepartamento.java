package controlador;

import java.util.Date;

import modelo.AreaConocimiento;
import modelo.Departamento;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;

import servicio.SAreaConocimiento;
import servicio.SDepartamento;
import componentes.BotoneraMaestros;

import configuracion.BeanServicios;

@Controller
public class CDepartamento extends CGenerico {
	
 SDepartamento servicioDepartamento = BeanServicios.getSDepartamento();
		

	public CDepartamento() {
		// TODO Auto-generated constructor stub
	}

	@Wire
	private Div botoneraEstandar;
	@Wire
	private Textbox txtNombreDepartamento;
	@Wire
	private Textbox txtDescripcionDepartamento;
	
	@Override
	void inicializar() {
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				// Metodo de guardar
				String nombrep = txtNombreDepartamento.getValue();	   
				String descripcionp = txtDescripcionDepartamento.getValue();
			    long idp=0;
			    boolean estadop=true; 

			    Departamento departamento = new Departamento(idp,nombrep,descripcionp,estadop);
			    servicioDepartamento.guardar(departamento);
			    System.out.println("guardado");
			}

			@Override
			public void limpiar() {
				// Metodo de limíar

			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {
				//

			}
		};
		botoneraEstandar.appendChild(botonera);

	}
}