package ec.edu.epn.nutricion.model.servicio;

import java.util.List;
import java.util.Map;

import ec.edu.epn.nutricion.entities.EntidadBase;

@SuppressWarnings({ "rawtypes"})
public interface ServicioGenerico<T> {
	/**
	 * 
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param entidad
	 * @throws AS2Exception
	 */
	void guardar(T entidad) throws Exception;

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
	List<T> obtenerListaCombo(Class claseEntidad, String sortField, boolean sortOrder, Map<String, String> filters);

	/**
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @return
	 */
	List<T> obtenerListaPorPagina(Class claseEntidad, int startIndex, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters);

	/**
	 * TODO: Agregar descripcion del metodo
	 * 
	 * @param filters
	 * @return
	 */
	int contarPorCriterio(Class claseEntidad, Map<String, String> filters);

	/**
	 * Recupera un banco dado su codigo
	 * 
	 * @param idOrganizacion
	 * @param codigo
	 * @return
	 * @throws ExcepcionAS2
	 */
	T buscarPorCodigo(Class claseEntidad, int idOrganizacion, String codigo) throws Exception;

	/**
	 * Retorna un elemento dado su nombre
	 * 
	 * @param idOrganizacion
	 * @param nombreBanco
	 * @return
	 * @throws ExcepcionAS2
	 */
	T buscarPorNombre(Class claseEntidad, int idOrganizacion, String nombre);

	List<T> obtenerListaPorPagina(Class claseEntidad, int startIndex, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters,
			List<String> listaCampos);

	T cargarDetalle(Class claseEntidad, int id, List<String> listaCampos);

	void eliminar(T entidad, List<? extends EntidadBase> listaDetalles);
}