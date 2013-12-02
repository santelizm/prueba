package controlador;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.AreaAcademica;
import modelo.AreaConocimiento;
import modelo.Departamento;
import modelo.TutorAcademico;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import servicio.SAreaAcademica;
import servicio.SAreaConocimiento;
import servicio.SDepartamento;
import componentes.BotoneraMaestros;
import configuracion.BeanServicios;
import componentes.Catalogo;

@Controller
public class CAreaAcademica extends CGenerico {

	

	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div divContenedorCatalogo;
	SAreaAcademica servicioAreaAcademica = BeanServicios.getSAreaAcademica();
	SDepartamento servicioDepartamento = BeanServicios.getSDepartamento();

	public CAreaAcademica() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void inicializar() {
		comboDepartamento();
		//listadoAreaAcademica();
		//listaAreaAcademica.setVisible(false);
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				// Metodo de guardar
				
				String nombre = txtNombreAreaAcademica.getValue();
				String descripcion = txtDescripcionAreaAcademica.getValue();
				long id = 0;
				boolean estado = true;
				String nombreDepartamento = cmbDepartamento.getValue();
				Departamento departamento = servicioDepartamento
						.buscarPorNombreDepartamento(nombreDepartamento);
				
				String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
				AreaAcademica nuevaAreaAcademica = new AreaAcademica(id,
						departamento, nombre, descripcion, nombre, fecha,
						horaAuditoria, estado);
				servicioAreaAcademica.guardar(nuevaAreaAcademica);

				System.out.print("Guardado");
				limpiar();
			}

			@Override
			public void limpiar() {
				txtIdAreaAcademica.setValue("");
				txtNombreAreaAcademica.setValue("");
				txtDescripcionAreaAcademica.setValue("");
				cmbDepartamento.setValue("");
			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {
				//
				String idAreaAcademica=txtIdAreaAcademica.getValue();
				AreaAcademica areaAcademica=servicioAreaAcademica.buscarAreaAcademica(Long.valueOf(idAreaAcademica));
				areaAcademica.setEstadoEliminacion(false);
				servicioAreaAcademica.guardar(areaAcademica);
				limpiar();
			}
		};
		botoneraEstandar.appendChild(botonera);

	}

	public void comboDepartamento() {
		List<Departamento> departamento = servicioDepartamento
				.buscarDepartamentosActivos();
		cmbDepartamento.setModel(new ListModelList<Departamento>(departamento));

	}
//
//	public void listadoAreaAcademica() {
//
//		List<AreaAcademica> areaAcademica = servicioAreaAcademica
//				.buscarAreasActivas();
//		listaAreaAcademica.setModel(new ListModelList<AreaAcademica>(
//				areaAcademica));
//
//	}

	@Listen("onClick = #btnBuscarArea")
	public void mostrarCatalogo() {
		List<AreaAcademica> areaAcademica = servicioAreaAcademica.buscarAreasActivas();
		Catalogo catalagoAreaAcademica = new Catalogo<AreaAcademica>(areaAcademica, "Nombre", "Descripcion", "Depastamento"){

			@Override
			protected String[] crearRegistros(AreaAcademica areasAcademicas) {
				// TODO Auto-generated method stub
				String[] registros = new String[3];
				registros[0] = areasAcademicas.getNombre();
				registros[1] = areasAcademicas.getDescripcion();
				registros[2] = String.valueOf(areasAcademicas.getDepartamento());
				return registros;
			}
			
		};
		divContenedorCatalogo.appendChild(catalagoAreaAcademica);
		//listadoAreaAcademica();

		//listaAreaAcademica.setVisible(true);

	/*	AreaAcademica areaAcademica = new AreaAcademica();

		txtNombreAreaAcademica.setValue(areaAcademica.getNombre());
		txtDescripcionAreaAcademica.setValue(areaAcademica.getDescripcion());
		 cmbDepartamento.setValue(areaAcademica.getDepartamento().getNombre());
*/
	}

	@Listen("onDoubleClick = #listaAreaAcademica")
	public void seleccionarListado() {
		// get the selected object
		AreaAcademica areaAcademica = listaAreaAcademica.getSelectedItem()
				.getValue();
		AreaAcademica areaAcademica2=servicioAreaAcademica.buscarAreaAcademica(areaAcademica.getId());
		txtIdAreaAcademica.setValue(Long.toString(areaAcademica2.getId()));
		txtNombreAreaAcademica.setValue(areaAcademica2.getNombre());
		txtDescripcionAreaAcademica.setValue(areaAcademica2.getDescripcion());
		cmbDepartamento.setValue(areaAcademica2.getDepartamento().getNombre());

		listaAreaAcademica.setVisible(false);

	}
}
