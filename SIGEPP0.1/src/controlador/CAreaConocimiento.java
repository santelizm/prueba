package controlador;

import java.awt.Button;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.AreaAcademica;
import modelo.AreaConocimiento;
import modelo.Departamento;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import servicio.SAreaConocimiento;
import componentes.BotoneraMaestros;
import componentes.BotoneraMaestros;
import componentes.Catalogo;
import configuracion.BeanServicios;

@Controller
public class CAreaConocimiento extends CGenerico {

	SAreaConocimiento servicioAreaConocimiento = BeanServicios.getSAreaConocimiento();
	public CAreaConocimiento() {
		// TODO Auto-generated constructor stub
	}

	@Wire
	private Div botoneraEstandar;
	@Wire
	private Textbox txtNombreAreaConocimiento;
	@Wire
	private Textbox txtIdAreaConocimiento;
	@Wire
	private Textbox txtDescripcionAreaConocimiento;
	@Wire
	private Button btnBuscarAreaConocimiento;
	@Wire
	private Listbox listaAreaConocimiento;
	@Wire
	private Div divContenedorCatalogo;	
	@Override
	void inicializar() {
		//listaAreaConocimiento.setVisible(false);
		//listadoAreaConocimiento();
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				// Metodo de guardar
				String nombre = txtNombreAreaConocimiento.getValue();	   
				String descripcion = txtDescripcionAreaConocimiento.getValue();
				
				boolean estado= true;
				long id=0;
				String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
				
				AreaConocimiento areaConocimiento = new AreaConocimiento(id,nombre,descripcion,nombre, fecha,horaAuditoria,true);
				servicioAreaConocimiento.guardar(areaConocimiento);
			 
        System.out.println("guardado");
        limpiar();
			}

			@Override
			public void limpiar() {
				// Metodo de limíar

				txtIdAreaConocimiento.setValue("");
				txtNombreAreaConocimiento.setValue("");
				txtDescripcionAreaConocimiento.setValue("");
			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {
				
			String idAreaConocimiento=txtIdAreaConocimiento.getValue();
			AreaConocimiento areaConocimiento=servicioAreaConocimiento.buscarAreaConocimiento(Long.valueOf(idAreaConocimiento));
			areaConocimiento.setEstadoEliminacion(false);
			servicioAreaConocimiento.guardar(areaConocimiento);
			limpiar();
			}
		};
		botoneraEstandar.appendChild(botonera);

	}
//	public void listadoAreaConocimiento() {
//		
//		List<AreaConocimiento> areaAcademica = servicioAreaConocimiento.buscarAreasActivas();
//		listaAreaConocimiento.setModel(new ListModelList<AreaConocimiento>(areaAcademica));
//
//		}


			 @Listen("onClick = #btnBuscarAreaConocimiento")
			 public void mostrarCatalogo(){
				 List<AreaConocimiento> areaConocimiento = servicioAreaConocimiento.buscarAreasActivas();
					Catalogo catalagoAreaConocimento = new Catalogo<AreaConocimiento>(areaConocimiento, "Id", "Nombre", "Descripcion"){

						@Override
						protected String[] crearRegistros(
								AreaConocimiento areasConocimiento) {
							// TODO Auto-generated method stub
							String registros[] = new String[3];
							registros[0] = String.valueOf(areasConocimiento.getId());
							registros[1] = areasConocimiento.getNombre();
							registros[2] = areasConocimiento.getDescripcion();
							return registros;
						}
						
					};
					divContenedorCatalogo.appendChild(catalagoAreaConocimento);
				 //listadoAreaConocimiento();
				 //listaAreaConocimiento.setVisible(true);
//				 AreaConocimiento areaConocimiento= new  AreaConocimiento();
//				 		
//					 txtNombreAreaConocimiento.setValue(areaConocimiento.getNombre());
//					 txtDescripcionAreaConocimiento.setValue(areaConocimiento.getDescripcion());
			
				
			 }
			
		
			 @Listen("onClick = #listaAreaConocimiento")
				public void seleccion(){
				
				 AreaConocimiento areaConocimiento= listaAreaConocimiento.getSelectedItem().getValue();
				 AreaConocimiento areaConocimiento2=servicioAreaConocimiento.buscarPorNombreAreaConocimiento(areaConocimiento.getNombre());
				 txtIdAreaConocimiento.setValue(Long.toString(areaConocimiento2.getId()));
				 txtNombreAreaConocimiento.setValue(areaConocimiento2.getNombre());
				 txtDescripcionAreaConocimiento.setValue(areaConocimiento2.getDescripcion());
			 	 listaAreaConocimiento.setVisible(false);
			 
			 }
}
