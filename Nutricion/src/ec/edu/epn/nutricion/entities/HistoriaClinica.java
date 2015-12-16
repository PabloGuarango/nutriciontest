package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;


import java.util.Date;


/**
 * The persistent class for the historia_clinica database table.
 * 
 */
@Entity
@Table(name="historia_clinica")
@NamedQuery(name="HistoriaClinica.findAll", query="SELECT h FROM HistoriaClinica h")
public class HistoriaClinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_HISTORIA_CLINICA")
	private int idHistoriaClinica;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to DatosMedico
	@ManyToOne
	@JoinColumn(name="ID_DATOS_MEDICOS")
	private DatosMedico datosMedico;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="ID_PACIENTE")
	private Paciente paciente;

	public HistoriaClinica() {
	}

	public int getIdHistoriaClinica() {
		return this.idHistoriaClinica;
	}

	public void setIdHistoriaClinica(int idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public DatosMedico getDatosMedico() {
		return this.datosMedico;
	}

	public void setDatosMedico(DatosMedico datosMedico) {
		this.datosMedico = datosMedico;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}