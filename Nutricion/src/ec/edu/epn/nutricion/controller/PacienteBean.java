package ec.edu.epn.nutricion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;

import ec.edu.epn.nutricion.entities.Paciente;
import ec.edu.epn.nutricion.model.servicio.ServicioPaciente;
import ec.edu.epn.nutricion.utils.FuncionesUtiles;

@ManagedBean
@ViewScoped
public class PacienteBean {
	@EJB
	private ServicioPaciente servicioPaciente;

	private Paciente paciente;
	private List<Paciente> listaPaciente;
	private DataTable dtPacientes;
	private boolean editado = false;
	@PostConstruct
	public void init() {
		getListaPaciente();
	}
	public String guardarPaciente(){
		servicioPaciente.insertar(paciente);
		editado=false;
		getListaPaciente();
		return null;
	}
	public String crear() {
		paciente = new Paciente();
		editado = true;
		return null;
	}
	public String editarPaciente() {
		paciente = (Paciente) dtPacientes.getRowData();
		paciente = servicioPaciente.buscarPorId(paciente.getIdPaciente());
		System.out.println(paciente);
		editado = true;
		return null;
	}
	public String calcular() {
		if (paciente.getFechaNacimiento() != null)
			paciente.setEdad(FuncionesUtiles.calcularEdad(paciente.getFechaNacimiento(), new Date()));
		return null;
	}
	public String eliminarPaciente() {
		Paciente paci = (Paciente) dtPacientes.getRowData();
		servicioPaciente.eliminar(paci);
		return null;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<Paciente> getListaPaciente() {
		Map<String, String> filters=new HashMap<String, String>();
		listaPaciente = servicioPaciente.obtenerListaCombo("apellidos", true, filters);
		return listaPaciente;
	}
	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}
	public DataTable getDtPacientes() {
		return dtPacientes;
	}
	public void setDtPacientes(DataTable dtPacientes) {
		this.dtPacientes = dtPacientes;
	}
	public boolean isEditado() {
		return editado;
	}
	public void setEditado(boolean editado) {
		this.editado = editado;
	}
}
