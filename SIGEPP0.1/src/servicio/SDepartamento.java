package servicio;

import java.util.List;



import interfazdao.IDepartamentoDAO;



import modelo.Departamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SDepartamento {
	

	@Autowired
	private  IDepartamentoDAO departamentoDAO;

	public void guardar(Departamento departamento){
		departamentoDAO.save(departamento);
	}


	public  List<Departamento> buscarDepartamentosActivos(){
    	List<Departamento> departamentos;
    	departamentos = departamentoDAO.buscarDepartamentosActivos();
		return departamentos;
		
	}
	
	 public Departamento buscarPorNombreDepartamento(String nombre){
		   Departamento departamento;
		   departamento = departamentoDAO.findByNombre(nombre);
			return departamento;
			
		}
	
}
