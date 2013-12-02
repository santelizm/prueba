package modelo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "departamento")
public class Departamento {

	@GeneratedValue
	@Id
	@Column(name = "departamento_id", nullable = false)
	private long id;
	
	@Column(name = "nombre", length = 100)
	private String nombre;
	
	@Column(name = "descripcion", length = 500)
	private String descripcion;
	
	@Column(name = "estado_eliminacion")
	private boolean estadoEliminacion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento", fetch = FetchType.EAGER)
	private Set<AreaAcademica> areasAcademicas;
	

	public Departamento(long id, String nombre,
			String descripcion,boolean estadoEliminacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estadoEliminacion = estadoEliminacion;
	}


	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstadoEliminacion() {
		return estadoEliminacion;
	}


	public void setEstadoEliminacion(boolean estadoEliminacion) {
		this.estadoEliminacion = estadoEliminacion;
	}


	public Set<AreaAcademica> getAreasAcademicas() {
		return areasAcademicas;
	}


	public void setAreasAcademicas(Set<AreaAcademica> areasAcademicas) {
		this.areasAcademicas = areasAcademicas;
	}

		
}