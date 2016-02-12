package ec.edu.epn.nutricion.model.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.edu.epn.nutricion.entities.HistoriaClinica;
import ec.edu.epn.nutricion.entities.Paciente;
/**
 * DAO Para la entidad 'Paciente'
 * 
 * @UsuarioCreacion: Pablo Guarango
 * @Version: 1.0
 */

@Stateless
public class PacienteDao extends AbstractDao<Paciente> {
	/**
	 * Constructor por defecto que inicializa el AbstractDao con la clase entidad
	 */
	public PacienteDao() {
		super(Paciente.class);
	}
	/**
	 * 
	 * Retorna la entidad dado su id
	 * 
	 * @param idPuntoDeVenta
	 * @return
	 */
	@Override
	public Paciente buscarPorId(Object idPaciente) {
		
//		StringBuilder sqlPaciente = new StringBuilder();
//		sqlPaciente.append(" SELECT p ");
//		sqlPaciente.append(" FROM Paciente p ");
//		sqlPaciente.append(" LEFT JOIN p.antecedenteAlimentario al ");
//		sqlPaciente.append(" WHERE p.idPaciente=:idPaciente ");
//		Query query = em.createQuery(sqlPaciente.toString());
//		query.setParameter("idPaciente", idPaciente);
//		Paciente paciente=(Paciente) query.getSingleResult();
//		paciente.getIdPaciente();
//		StringBuilder sqlPatologias= new StringBuilder();
//		sqlPatologias.append(" SELECT C ");
//		sqlPatologias.append(" FROM PatologiaAsociada c ");
//		sqlPatologias.append(" LEFT JOIN c.paciente p ");
//		sqlPatologias.append(" WHERE p.idPaciente=:idPaciente ");
//		query = em.createQuery(sqlPatologias.toString());
//		query.setParameter("idPaciente", idPaciente);
//		paciente.setListaCirugias(query.getResultList());
//		paciente.getListaPatologiasAsociadas();
//		paciente.getListaAntecedentesSalud().size();
//		paciente.getListaCirugias().size();
//		paciente.getListaHistoriasClinicas().size();
//		StringBuilder sqlCirugias = new StringBuilder();
//		sqlCirugias.append(" SELECT C ");
//		sqlCirugias.append(" FROM Cirugia c ");
//		sqlCirugias.append(" LEFT JOIN c.paciente p ");
//		sqlCirugias.append(" WHERE p.idPaciente=:idPaciente ");
//		query = em.createQuery(sqlPaciente.toString());
//		query.setParameter("idPaciente", idPaciente);
//		paciente.setListaCirugias(query.getResultList());
//		
//		
//		
//		sql.append(" LEFT JOIN p.listaCirugias lc ");
//		sql.append(" LEFT JOIN p.listaAntecedenteSaluds las ");
//		sql.append(" LEFT JOIN p.listaHistoriaClinicas lhc ");
//		sql.append(" LEFT JOIN lhc.datosMedico dm ");
//		sql.append(" LEFT JOIN dm.antropometria an ");
//		sql.append(" LEFT JOIN dm.listaMedicamentos lmedi ");
//		sql.append(" LEFT JOIN dm.listaSuplementoNutricional lsn ");
//
//		sql.append(" LEFT JOIN p.listaPatologiaAsociadas lpa ");
//
//		
//		sql.append(" LEFT JOIN al.listaAlimentos lalim ");
//		sql.append(" LEFT JOIN al.listaIntoleranciaAlergicas lia ");
//		sql.append(" LEFT JOIN al.listaProblemaGastrointestinals lpg ");
		
		return super.buscarPorId(idPaciente);
	}
	public Paciente cargarDetalle(int id) {
		// Recupera una entidad manejada
		Paciente paciente = buscarPorId(id);
		paciente.getIdPaciente();
		paciente.getAntecedenteAlimentario().getIdAntecedenteAlimentario();
		paciente.getAntecedenteAlimentario().getListaProblemaGastrointestinals().size();
		paciente.getAntecedenteAlimentario().getListaAlimentos().size();
		paciente.getAntecedenteAlimentario().getListaIntoleranciaAlergicas().size();
		paciente.getListaAntecedentesSalud().size();
		paciente.getListaCirugias().size();
		paciente.getListaHistoriasClinicas().size();
		for (HistoriaClinica hc : paciente.getListaHistoriasClinicas()) {
			hc.getIdHistoriaClinica();
			hc.getDatosMedico().getListaAntropometria().size();
		}
		paciente.getListaPatologiasAsociadas().size();
		return paciente;
	}
	@Override
	public void insertar(Paciente entidad) {
		super.insertar(entidad);
	}
	@Override
	public void actualizar(Paciente entidad) {
		super.actualizar(entidad);
	}
	@Override
	public void eliminarAnular(Paciente entidad) {
		super.eliminarAnular(entidad);
	}
	@Override
	public void eliminar(Paciente entidad) {
		super.eliminar(entidad);
	}
	@Override
	public void refrescar(Paciente entidad) {
		super.refrescar(entidad);
	}
	@Override
	public void detach(Paciente entidad) {
		super.detach(entidad);
	}
	@Override
	public void flush() {
		super.flush();
	}
	@Override
	public void guardar(Paciente entidad) {
		super.guardar(entidad);
	}
	@Override
	public List<Paciente> obtenerListaCombo(String sortField, boolean sortOrder, Map<String, String> filtros) {
		return super.obtenerListaCombo(sortField, sortOrder, filtros);
	}
	
}
