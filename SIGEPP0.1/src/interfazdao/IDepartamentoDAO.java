package interfazdao;

import java.util.List;

import modelo.AreaAcademica;
import modelo.Departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDepartamentoDAO  extends JpaRepository<Departamento, String> {
	
	@Query("select d from Departamento d where d.estadoEliminacion='true'")
	public List<Departamento> buscarDepartamentosActivos();
	
	public Departamento findByNombre(String nombre);
}
