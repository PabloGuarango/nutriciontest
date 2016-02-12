package ec.edu.epn.nutricion.model.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import ec.edu.epn.nutricion.entities.EntidadBase;

/**
 * TODO: Agregar descripcion
 * 
 * @FechaCreacion: 05/11/2015
 * @UsuarioCreacion: Andres Guarango
 * @Version: 1.0
 */
@Stateless
@SuppressWarnings("unchecked")
public class GenericoDao<T extends EntidadBase> extends AbstractDao<T> {

	public GenericoDao() {
		super((Class<T>) EntidadBase.class);
	}

	/**
	 * Persite el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void insertar(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.insertar(entidad);
	}

	/**
	 * Actualiza el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void actualizar(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.actualizar(entidad);
	}

	/**
	 * Elimina el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void eliminarAnular(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.eliminarAnular(entidad);
	}

	/**
	 * Elimina un objeto de la base de datos
	 * 
	 * @param entidad
	 */
	public void eliminar(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.eliminar(entidad);
	}

	/**
	 * Retorna la entidad dado su id
	 * 
	 * @param id
	 * @return
	 */
	public T buscarPorId(Class claseEntidad, Object id) {
		this.claseEntidad = claseEntidad;
		return super.buscarPorId(id);
	}

	/**
	 * Actualizar el estado de la instancia de la base de datos, sobrescribiendo los cambios realizados a la entidad
	 * 
	 * @param entidad
	 */
	public void refrescar(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.refrescar(entidad);
	}

	/**
	 * Eliminar la entidad dada desde el contexto de persistencia
	 * 
	 * @param entidad
	 */
	public void detach(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.detach(entidad);
	}

	/**
	 * 
	 * Escribe los datos en la BDD
	 */
	public void flush(Class claseEntidad) {
		this.claseEntidad = claseEntidad;
		super.flush();
	}
	/**
	 * Guarada el objeto en la Base de Datos
	 * 
	 * @param entidad
	 * @throws AS2Exception
	 */
	public void guardar(T entidad) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.guardar(entidad);
	}
	public String buscarPorNombre(Class claseEntidad, T entidad) {

		String nombre = null;
		try {
			Object[] parametros = null;
			Method metodo2 = claseEntidad.getMethod("getNombre");
			nombre = (String) metodo2.invoke(entidad, parametros);
		} catch (Exception e) {
			return null;
		}
		if (nombre == null) {
			return null;
		}

		Map<String, String> filtros = new HashMap<String, String>();
		filtros.put("nombre", "=" + nombre);
		List<T> lista = obtenerListaCombo(claseEntidad, "nombre", true, filtros);
		if (lista.size() > 0 && entidad.getId() != lista.get(0).getId()) {
			return nombre;
		} else {
			return null;
		}
	}
	/**
	 * Cuenta el número de registros de una entidad
	 * 
	 * @param filters
	 * @return
	 */
	public int contarPorCriterio(Class claseEntidad, Map<String, String> filtros) {
		this.claseEntidad = claseEntidad;
		return super.contarPorCriterio(filtros);
	}

	/**
	 * 
	 * @param sortField
	 * @param sortOrder
	 * @param filtros
	 * @return
	 */
	public List<T> obtenerListaCombo(Class claseEntidad, String sortField, boolean sortOrder, Map<String, String> filtros) {
		this.claseEntidad = claseEntidad;
		return super.obtenerListaCombo(sortField, sortOrder, filtros);
	}

	/**
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filtros
	 * @return
	 */
	public List<T> obtenerListaPorPagina(Class claseEntidad, int startIndex, int pageSize, String sortField, boolean sortOrder,
			Map<String, String> filtros) {
		this.claseEntidad = claseEntidad;
		return super.obtenerListaPorPagina(startIndex, pageSize, sortField, sortOrder, filtros);
	}

	/**
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filtros
	 * @param listaCampos
	 * @return
	 */
	public List<T> obtenerListaPorPagina(Class claseEntidad, int startIndex, int pageSize, String sortField, boolean sortOrder,
			Map<String, String> filtros, List<String> listaCampos) {
		if (listaCampos == null) {
			listaCampos = new ArrayList<String>();
		}
		this.claseEntidad = claseEntidad;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(claseEntidad);
		Root<T> from = cq.from(claseEntidad);

		for (String campo : listaCampos) {
			String[] propiedades = campo.split("\\.");
			String propiedad = propiedades[propiedades.length - 1];
			Fetch<Object, Object> fromFetch = null;
			if (propiedades.length > 1) {
				for (int i = 0; i < propiedades.length - 1; i++) {
					if (fromFetch == null) {
						fromFetch = from.fetch(propiedades[i], JoinType.LEFT);
					} else {
						fromFetch = fromFetch.fetch(propiedades[i], JoinType.LEFT);
					}
				}
			}
			if (fromFetch == null) {
				fromFetch = from.fetch(propiedad, JoinType.LEFT);
			} else {
				fromFetch = fromFetch.fetch(propiedad, JoinType.LEFT);
			}
		}

		// Ordena
		agregarOrdenamiento(sortField, sortOrder, cb, cq, from);

		// Agrega los Filtros
		agregarFiltros(filtros, cb, cq, from);

		// Agrega la paginacion
		TypedQuery<T> typedQuery = em.createQuery(cq.select(from));
		agregarPaginacion(startIndex, pageSize, typedQuery);

		return typedQuery.getResultList();
	}

	public T cargarDetalle(Class claseEntidad, int id, List<String> listaCampos) {
		if (listaCampos == null) {
			listaCampos = new ArrayList<String>();
		}
		this.claseEntidad = claseEntidad;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(claseEntidad);
		Root<T> from = cq.from(claseEntidad);

		for (String campo : listaCampos) {
			String[] propiedades = campo.split("\\.");
			String propiedad = propiedades[propiedades.length - 1];
			Fetch<Object, Object> fromFetch = null;
			if (propiedades.length > 1) {
				for (int i = 0; i < propiedades.length - 1; i++) {
					if (fromFetch == null) {
						fromFetch = from.fetch(propiedades[i], JoinType.LEFT);
					} else {
						fromFetch = fromFetch.fetch(propiedades[i], JoinType.LEFT);
					}
				}
			}
			if (fromFetch == null) {
				fromFetch = from.fetch(propiedad, JoinType.LEFT);
			} else {
				fromFetch = fromFetch.fetch(propiedad, JoinType.LEFT);
			}
		}

		Path<Integer> pathId = from.get("id" + claseEntidad.getSimpleName());
		cq.where(cb.equal(pathId, id));

		TypedQuery<T> typedQuery = em.createQuery(cq.select(from));

		return typedQuery.getSingleResult();
	}

	/**
	 * Recupera el tipo de dato de un atributo de la entidad
	 * 
	 * @param claseRoot
	 * @param propiedadRoot
	 * @return
	 */
	public Class<?> getTipoDato(Class claseEntidad, Class<?> claseRoot, String propiedadRoot) {
		this.claseEntidad = claseEntidad;
		return super.getTipoDato(claseRoot, propiedadRoot);
	}

	/**
	 * Actauliza los capos de una entidad
	 * 
	 * @param entidad
	 *            Entidad a Actualizar
	 * @param campos
	 *            HashMap <"campo", valor>
	 */
	public void actualizarAtributoEntidad(T entidad, HashMap<String, Object> campos) {
		this.claseEntidad = (Class<T>) entidad.getClass();
		super.actualizarAtributoEntidad(entidad, campos);
	}
}
