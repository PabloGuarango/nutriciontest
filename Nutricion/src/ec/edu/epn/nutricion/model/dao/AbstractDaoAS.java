package ec.edu.epn.nutricion.model.dao;

import java.util.List;

import javax.persistence.Query;

import ec.edu.epn.nutricion.model.db.NutricionDBBase;

/**
 * Dao Abstracto
 * 
 * @FechaCreacion: 05/11/2015
 * @UsuarioCreacion: Andres Guarango
 * @Version: 1.1
 */
public abstract class AbstractDaoAS<T> extends NutricionDBBase {
	protected Class<T> claseEntidad;

	public AbstractDaoAS(Class<T> claseEntidad) {
		this.claseEntidad = claseEntidad;
	}
	public AbstractDaoAS() {
		
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
	
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String claseEntidad2) {

		Query query = em.createQuery("SELECT c FROM "+claseEntidad2+" c ");
		return query.getResultList();
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
}
