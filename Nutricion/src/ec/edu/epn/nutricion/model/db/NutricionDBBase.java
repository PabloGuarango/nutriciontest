package ec.edu.epn.nutricion.model.db;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * Clase base para obtener un entity manager de conexion a la base de datos del
 * ERP
 * 
 * @author Andres Guarango
 * @version 1.0
 */
public class NutricionDBBase {
	@PersistenceContext
	protected EntityManager em;
}
