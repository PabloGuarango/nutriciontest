package ec.edu.epn.nutricion.model.servicio;

import java.util.List;
import java.util.Map;


public interface ServicioGenerico<T> {
	/**
	 * 
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param entidad
	 * @throws AS2Exception
	 */
	void guardar(T entidad);
	/**
	 * 
	 * TODO: Actualiza en la base
	 * 
	 * @param entidad
	 * @throws AS2Exception
	 */
	void actualizar(T entidad);
	/**
	 * 
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param entidad
	 */
	void eliminar(T entidad);

	/**
	 * 
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param id
	 * @return
	 */
	T buscarPorId(Class claseEntidad, Integer id);

	/**
	 * 
	 * Retorna un listado Es utilizado paar cargar en un combobox
	 * 
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @return
	 */
	List<T> obtenerListaCombo(String claseEntidad);

}
