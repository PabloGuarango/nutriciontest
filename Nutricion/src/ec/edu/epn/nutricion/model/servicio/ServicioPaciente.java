package ec.edu.epn.nutricion.model.servicio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import ec.edu.epn.nutricion.entities.Paciente;

/**
 * Interface del EJB que carga al paciente con todos sus atributos
 * 
 * @author Pablo Guarango
 * @version 1.0
 */
@Local
public interface ServicioPaciente {
	/**
	 * Retorna un paciente con todos sus atributos
	 * 
	 * @param id
	 * @return
	 */
	Paciente cargarDetalle(int id);
	/**
	 * Retorna un paciente con todos sus atributos
	 * 
	 * @param id
	 * @return
	 */
	Paciente buscarPorId(int id);
	void insertar(Paciente entidad);
	void actualizar(Paciente entidad);
	void eliminarAnular(Paciente entidad);
	public void eliminar(Paciente entidad);
	public void refrescar(Paciente entidad);
	public void detach(Paciente entidad);
	public void flush();
	List<Paciente> obtenerListaCombo(String sortField, boolean sortOrder, Map<String, String> filters);
}
