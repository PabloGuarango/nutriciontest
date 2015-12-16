package ec.edu.epn.nutricion.model.dao;

import javax.ejb.Stateless;

/**
 * TODO: Agregar descripcion
 * 
 * @FechaCreacion: 05/11/2015
 * @UsuarioCreacion: Andres Guarango
 * @Version: 1.0
 */
@Stateless
public class GenericoDao<T> extends AbstractDaoAS<T> {


	public GenericoDao(Class<T> claseEntidad) {
		super((Class<T>) claseEntidad.getClass());
	}
	

	public GenericoDao() {
		super();
		// TODO Auto-generated constructor stub
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
	 * Actualizar el estado de la instancia de la base de datos, sobrescribiendo
	 * los cambios realizados a la entidad
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

}
