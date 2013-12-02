package configuracion;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servicio.SAreaAcademica;
import servicio.SAreaConocimiento;
import servicio.SAreaTutorAcademico;
import servicio.SDepartamento;

import servicio.STutorAcademico;


public class BeanServicios implements ApplicationContextAware {

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/META-INF/ContextoAplicacion.xml");

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BeanServicios.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	
	
	public static SDepartamento getSDepartamento() {
		return applicationContext.getBean(SDepartamento.class);
	}
	public static STutorAcademico getSTutorAcademico() {
		return applicationContext.getBean(STutorAcademico.class);
	}
	public static SAreaAcademica getSAreaAcademica() {
		return applicationContext.getBean(SAreaAcademica.class);
	}

	public static SAreaConocimiento getSAreaConocimiento() {
		return applicationContext.getBean(SAreaConocimiento.class);
	}
	public static SAreaTutorAcademico getSAreaTutorAcademico() {
		return applicationContext.getBean(SAreaTutorAcademico.class);
	}
}
