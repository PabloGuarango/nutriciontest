package ec.edu.epn.nutricion.model.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.edu.epn.nutricion.model.dao.GenericoDao;
import ec.edu.epn.nutricion.model.servicio.ServicioGenerico;


@Stateless
public class ServicioGenericoImpl<T> implements ServicioGenerico<T> {
	@EJB
	private GenericoDao genericoDao;

	@Override
	public void guardar(T entidad) {
		genericoDao.insertar(entidad);
	}

	@Override
	public void eliminar(T entidad) {
		genericoDao.eliminar(entidad);

	}

	@Override
	public T buscarPorId(Class claseEntidad, Integer id) {

		return (T) genericoDao.buscarPorId(claseEntidad, id);
	}

	@Override
	public List<T> obtenerListaCombo(String claseEntidad) {
		return genericoDao.buscarTodos(claseEntidad);
	}

	@Override
	public void actualizar(T entidad) {
		genericoDao.actualizar(entidad);
		
	}

}
