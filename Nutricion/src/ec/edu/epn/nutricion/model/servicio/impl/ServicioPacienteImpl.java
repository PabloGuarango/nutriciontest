package ec.edu.epn.nutricion.model.servicio.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.edu.epn.nutricion.entities.Paciente;
import ec.edu.epn.nutricion.model.dao.PacienteDao;
import ec.edu.epn.nutricion.model.servicio.ServicioPaciente;

/**
 * Clase del EJB que implementa las funcionalidades que carga al paciente con todos sus atributos
 * 
 * @author Pablo Guarango
 * @version 1.0
 */
@Stateless
public class ServicioPacienteImpl implements ServicioPaciente {
	@EJB
	private PacienteDao pacienteDao;
	@Override
	public Paciente cargarDetalle(int id) {
		return pacienteDao.cargarDetalle(id);
	}
	@Override
	public Paciente buscarPorId(int id) {
		return pacienteDao.buscarPorId(id);
	}
	@Override
	public void insertar(Paciente entidad) {
		pacienteDao.insertar(entidad);
	}
	@Override
	public void actualizar(Paciente entidad) {
		pacienteDao.actualizar(entidad);
	}
	@Override
	public void eliminarAnular(Paciente entidad) {
		pacienteDao.eliminarAnular(entidad);
	}
	@Override
	public void eliminar(Paciente entidad) {
		pacienteDao.eliminar(entidad);
	}
	@Override
	public void refrescar(Paciente entidad) {
		pacienteDao.refrescar(entidad);
	}
	
	@Override
	public void detach(Paciente entidad) {
		pacienteDao.detach(entidad);
	}
	@Override
	public void flush() {
		pacienteDao.flush();
	}
	@Override
	public List<Paciente> obtenerListaCombo(String sortField, boolean sortOrder, Map<String, String> filters) {
		return pacienteDao.obtenerListaCombo(sortField, sortOrder, filters);
	}
	@Override
	public void guardar(Paciente entidad) {
		pacienteDao.guardar(entidad);
	}
	
}
