package ec.edu.epn.nutricion.model.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.edu.epn.nutricion.entities.EntidadBase;
import ec.edu.epn.nutricion.model.db.NutricionDBBase;

/**
 * Dao Abstracto
 * 
 * @FechaCreacion: 05/11/2015
 * @UsuarioCreacion: Andres Guarango
 * @Version: 1.1
 */
public abstract class AbstractDao<T extends EntidadBase> extends NutricionDBBase {
	
	protected Class<T> claseEntidad;

	public AbstractDao(Class<T> claseEntidad) {
		this.claseEntidad = claseEntidad;
	}

	/**
	 * Persite el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void insertar(T entidad) {
		em.persist(entidad);
	}

	/**
	 * Actualiza el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void actualizar(T entidad) {
		em.merge(entidad);
	}

	/**
	 * Elimina el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void eliminarAnular(T entidad) {
		em.remove(em.merge(entidad));
	}

	/**
	 * Elimina un objeto de la base de datos
	 * 
	 * @param entidad
	 */
	public void eliminar(T entidad) {
		em.remove(em.merge(entidad));
	}

	/**
	 * Retorna la entidad dado su id
	 * 
	 * @param id
	 * @return
	 */
	public T buscarPorId(Object id) {
		return em.find(claseEntidad, id);
	}

	/**
	 * Actualizar el estado de la instancia de la base de datos, sobrescribiendo los cambios realizados a la entidad
	 * 
	 * @param entidad
	 */
	public void refrescar(T entidad) {
		em.refresh(entidad);
	}

	/**
	 * Eliminar la entidad dada desde el contexto de persistencia
	 * 
	 * @param entidad
	 */
	public void detach(T entidad) {
		em.detach(entidad);
	}

	/**
	 * 
	 * Escribe los datos en la BDD
	 */
	public void flush() {
		em.flush();
	}

	/**
	 * Guarada el objeto en la Base de Datos
	 * 
	 * @param entidad
	 */
	public void guardar(T entidad) {
		if (entidad.isEliminado()) {
			if (entidad.getId() != 0) {
				eliminar(entidad);
			}

		} else {
			if (entidad.getId() == 0) {
				insertar(entidad);
			} else {
				actualizar(entidad);
			}
		}
	}

	/**
	 * Cuenta el número de registros de una entidad
	 * 
	 * @param filters
	 * @return
	 */
	public int contarPorCriterio(Map<String, String> filtros) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> from = cq.from(claseEntidad);
		cq.select(cb.count(from));

		// Agrega los Filtros
		List<Expression<?>> expresiones = obtenerExpresiones(filtros, cb, from);

		if (!expresiones.isEmpty()) {
			cq.where(expresiones.toArray(new Predicate[expresiones.size()]));
		}

		return em.createQuery(cq).getSingleResult().intValue();
	}

	/**
	 * 
	 * @param sortField
	 * @param sortOrder
	 * @param filtros
	 * @return
	 */
	public List<T> obtenerListaCombo(String sortField, boolean sortOrder, Map<String, String> filtros) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(claseEntidad);
		Root<T> from = cq.from(claseEntidad);

		// Agrega los Filtros
		agregarFiltros(filtros, cb, cq, from);

		// Ordena
		agregarOrdenamiento(sortField, sortOrder, cb, cq, from);

		// Agrega la paginacion
		TypedQuery<T> typedQuery = em.createQuery(cq.select(from));
		agregarPaginacion(0, 20, typedQuery);

		return em.createQuery(cq).getResultList();
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
	public List<T> obtenerListaPorPagina(int startIndex, int pageSize, String sortField, boolean sortOrder, Map<String, String> filtros) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(claseEntidad);
		Root<T> from = cq.from(claseEntidad);

		// Ordena
		agregarOrdenamiento(sortField, sortOrder, cb, cq, from);

		// Agrega los Filtros
		agregarFiltros(filtros, cb, cq, from);

		// Agrega la paginacion
		TypedQuery<T> typedQuery = em.createQuery(cq.select(from));
		agregarPaginacion(startIndex, pageSize, typedQuery);

		return typedQuery.getResultList();
	}

	/**
	 * Establece el criterio de ordenamiento
	 * 
	 * @param sortField
	 * @param sortOrder
	 * @param cb
	 * @param cq
	 * @param from
	 */
	protected void agregarOrdenamiento(String sortField, boolean sortOrder, CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> from) {

		if (sortField != null && sortField.length()>0) {
			
			Path<String> path = null;
			String[] properties = sortField.split("\\.");

			if (properties.length == 1) {
				path = from.get(sortField);
			} else {

				sortField = properties[0];
				Join<Object, Object> joinTable = from.join(sortField, JoinType.LEFT);

				for (int i = 1; i < properties.length; i++) {
					sortField = properties[i];
					if (i < properties.length - 1) {
						joinTable = joinTable.join(sortField, JoinType.LEFT);
					} else {
						path = joinTable.get(sortField);
					}
				}
			}

			if (path == null || sortOrder) {
				cq.select(from).orderBy(cb.asc(path));
			} else {
				cq.select(from).orderBy(cb.desc(path));
			}
		}
	}

	/**
	 * Agrega la paginacion al Query
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @param typedQuery
	 */
	public void agregarPaginacion(int startIndex, int pageSize, TypedQuery<T> typedQuery) {
		typedQuery.setFirstResult(startIndex);
		typedQuery.setMaxResults(pageSize);
	}

	/**
	 * Retorna un listado
	 * 
	 * @param filtros
	 * @param criteriaBuilder
	 * @param criteriaQuery
	 * @param fromRoot
	 */
	protected List<Expression<?>> obtenerExpresiones(Map<String, String> filtros, CriteriaBuilder criteriaBuilder, Root<T> fromRoot) {

		// TODO Los filtros de la organizacion. Deben venir desde la presentacion, se mateniene por compabilidad

		List<Expression<?>> expresiones = new ArrayList<Expression<?>>();

		// Itera los filtros
		for (String propiedadFiltro : filtros.keySet()) {

			String valorFiltro = filtros.get(propiedadFiltro);

			// Recupera el tipo de dato de la propiedad
			Class<?> tipoDato = getTipoDato(claseEntidad, propiedadFiltro);

			if (propiedadFiltro != null && !propiedadFiltro.isEmpty()) {

				From<?, ?> from = fromRoot;
				String[] propiedades = propiedadFiltro.split("\\.");
				String propiedad = propiedades[propiedades.length - 1];

				if (propiedades.length > 1) {
					for (int i = 0; i < propiedades.length - 1; i++) {
						from = from.join(propiedades[i]);
					}
				}

				Expression<?> expresion = obtenerExpresion(tipoDato, from, criteriaBuilder, propiedad, valorFiltro);

				if (expresion != null) {
					expresiones.add(expresion);
				}
			}
		}

		return expresiones;
	}

	/**
	 * Agrega los filtros al Query
	 * 
	 * @param filtros
	 * @param criteriaBuilder
	 * @param criteriaQuery
	 * @param fromRoot
	 */
	protected void agregarFiltros(Map<String, String> filtros, CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> fromRoot) {

		List<Expression<?>> expresiones = obtenerExpresiones(filtros, criteriaBuilder, fromRoot);

		if (!expresiones.isEmpty()) {
			criteriaQuery.where(expresiones.toArray(new Predicate[expresiones.size()]));
		}		
	}

	/**
	 * Recupera una Expresion dado el nombreDelAtributo y su valor
	 * 
	 * @param tipoDato
	 * @param from
	 * @param criteriaBuilder
	 * @param propiedadFiltro
	 * @param valorFiltro
	 * @return
	 */
	private Expression<?> obtenerExpresion(Class<?> tipoDato, From<?, ?> from, CriteriaBuilder criteriaBuilder, String propiedadFiltro,
			String valorFiltro) {

		if (tipoDato == String.class) {
			DatoFiltro<String> datoFiltro = new DatoFiltro<String>(String.class, from, propiedadFiltro, valorFiltro);
			return datoFiltro.getExpresion(criteriaBuilder, datoFiltro);

		} else if (tipoDato == Integer.class || tipoDato.toString().equals("int") || tipoDato.toString().equals("short")) {
			DatoFiltro<Integer> datoFiltro = new DatoFiltro<Integer>(Integer.class, from, propiedadFiltro, valorFiltro);
			return datoFiltro.getExpresion(criteriaBuilder, datoFiltro);

		} else if (tipoDato == Long.class || tipoDato.toString().equals("long")) {
			DatoFiltro<Long> datoFiltro = new DatoFiltro<Long>(Long.class, from, propiedadFiltro, valorFiltro);
			return datoFiltro.getExpresion(criteriaBuilder, datoFiltro);

		} else if (tipoDato == BigDecimal.class) {
			DatoFiltro<BigDecimal> datoFiltro = new DatoFiltro<BigDecimal>(BigDecimal.class, from, propiedadFiltro, valorFiltro);
			return datoFiltro.getExpresion(criteriaBuilder, datoFiltro);

		} else if (tipoDato == Boolean.class || tipoDato.toString().equals("boolean")) {
			DatoFiltro<Boolean> datoFiltro = new DatoFiltro<Boolean>(Boolean.class, from, propiedadFiltro, valorFiltro);
			return datoFiltro.getExpresion(criteriaBuilder, datoFiltro);

		} else if (tipoDato == Date.class) {
			DatoFiltro<Date> datoFiltro = new DatoFiltro<Date>(Date.class, from, propiedadFiltro, valorFiltro);
			return datoFiltro.getExpresion(criteriaBuilder, datoFiltro);

		} else {
			return null;
		}

	}

	/**
	 * Recupera el tipo de dato de un atributo de la entidad
	 * 
	 * @param claseRoot
	 * @param propiedadRoot
	 * @return
	 */
	public Class<?> getTipoDato(Class<?> claseRoot, String propiedadRoot) {
		String[] datos = propiedadRoot.split("\\.");

		String propiedad = datos[0];

		Class<?> clase = em.getEntityManagerFactory().getMetamodel().entity(claseRoot).getAttribute(propiedad).getJavaType();

		if (datos.length > 1) {
			propiedad = propiedadRoot.replace(propiedad + ".", "");
			return getTipoDato(clase, propiedad);
		} else {
			return clase;
		}
	}

	/**
	 * 
	 * Retorna los dados relacionados de la entidad dado su id
	 * 
	 * Este método debe ser sobrescrito por cada implementación
	 * 
	 * @param id
	 * @return
	 */
	public T cargarDetalle(int id) {
		return buscarPorId(id);
	}


	/**
	 * Actualiza los capos de una entidad
	 * 
	 * @param entidad
	 *            Entidad a Actualizar
	 * @param campos
	 *            HashMap <"campo", valor>
	 */
	@SuppressWarnings("rawtypes")
	public void actualizarAtributoEntidad(T entidad, HashMap<String, Object> campos) {

		int indice = 1;
		String clase = entidad.getClass().getSimpleName();
		String idEntidad = "id" + clase;
		String sql = "";
		if (campos != null && !campos.isEmpty()) {

			sql = " UPDATE " + clase + " t SET ";
			Iterator it1 = campos.entrySet().iterator();
			while (it1.hasNext()) {
				Map.Entry e = (Map.Entry) it1.next();
				sql += " t." + e.getKey() + "=:valor" + indice + ",";
				indice++;
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += " WHERE t." + idEntidad + " =:id ";

			Query query = em.createQuery(sql);

			indice = 1;
			Iterator it2 = campos.entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry e = (Map.Entry) it2.next();
				query.setParameter("valor" + indice, e.getValue());
				indice++;
			}
			query.setParameter("id", entidad.getId());
			query.executeUpdate();
		}
	}

}
