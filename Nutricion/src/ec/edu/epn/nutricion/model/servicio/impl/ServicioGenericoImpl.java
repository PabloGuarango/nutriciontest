package ec.edu.epn.nutricion.model.servicio.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.edu.epn.nutricion.entities.EntidadBase;
import ec.edu.epn.nutricion.model.dao.GenericoDao;
import ec.edu.epn.nutricion.model.servicio.ServicioGenerico;


@Stateless
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ServicioGenericoImpl<T extends EntidadBase> implements ServicioGenerico<T> {
	@EJB
	private GenericoDao genericoDao;
	@EJB
	private GenericoDao<EntidadBase> detalleDao;

	@Override
	public void guardar(T entidad) throws Exception {
		genericoDao.guardar(entidad);
	}


	@Override
	public void eliminar(T entidad) {
		genericoDao.eliminar(entidad);
	}
	
	@Override
	public void eliminar(T entidad, List<? extends EntidadBase> listaDetalles) {
		for (EntidadBase detalle : listaDetalles) {
			detalleDao.eliminar(detalle);
		}
		genericoDao.eliminar(entidad);
	}

	@Override
	public T buscarPorId(Class claseEntidad, Integer id) {
		return (T) genericoDao.buscarPorId(claseEntidad, id);
	}

	@Override
	public List<T> obtenerListaCombo(Class claseEntidad, String sortField, boolean sortOrder, Map<String, String> filters) {
		return genericoDao.obtenerListaCombo(claseEntidad, sortField, sortOrder, filters);
	}

	@Override
	public List<T> obtenerListaPorPagina(Class claseEntidad, int startIndex, int pageSize, String sortField, boolean sortOrder,
			Map<String, String> filters) {
		return genericoDao.obtenerListaPorPagina(claseEntidad, startIndex, pageSize, sortField, sortOrder, filters);
	}

	@Override
	public List<T> obtenerListaPorPagina(Class claseEntidad, int startIndex, int pageSize, String sortField, boolean sortOrder,
			Map<String, String> filters, List<String> listaCampos) {
		return genericoDao.obtenerListaPorPagina(claseEntidad, startIndex, pageSize, sortField, sortOrder, filters, listaCampos);
	}
	
	@Override
	public T cargarDetalle(Class claseEntidad, int id, List<String> listaCampos) {
		return (T) genericoDao.cargarDetalle(claseEntidad, id, listaCampos);
	}

	@Override
	public int contarPorCriterio(Class claseEntidad, Map<String, String> filters) {
		return genericoDao.contarPorCriterio(claseEntidad, filters);
	}

	@Override
	public T buscarPorCodigo(Class claseEntidad, int idOrganizacion, String codigo) throws Exception {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put("codigo", "=" + codigo);
		List<T> lista = genericoDao.obtenerListaCombo(claseEntidad, "codigo", true, filtros);
		if (lista.size() == 1) {
			return lista.get(0);
		} else if (lista.size() > 1) {
			throw new Exception("Codigo repetido"+ codigo);
		} else {
			return null;
		}
	}

	@Override
	public T buscarPorNombre(Class claseEntidad, int idOrganizacion, String nombre) {
		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put("nombre", "=" + nombre);
		List<T> lista = genericoDao.obtenerListaCombo(claseEntidad, "nombre", true, filtros);
		if (lista.size() > 0) {
			return lista.get(0);
		} else {
			return null;
		}
	}

}
