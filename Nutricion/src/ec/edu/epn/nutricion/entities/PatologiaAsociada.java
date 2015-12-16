package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the patologia_asociada database table.
 * 
 */
@Entity
@Table(name = "patologia_asociada")
@NamedQuery(name = "PatologiaAsociada.findAll", query = "SELECT p FROM PatologiaAsociada p")
public class PatologiaAsociada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PATOLOGIA_ASOCIADA")
	private int idPatologiaAsociada;

	@Column(name = "DESCRIPCION_PATOLOGIA")
	private String descripcion;

	@Column(name = "NOMBRE_PATOLOGIA")
	private String nombre;

	// bi-directional many-to-one association to DatosMedico
	@ManyToOne
	@JoinColumn(name = "ID_DATOS_MEDICOS")
	private DatosMedico datosMedico;
	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	private Paciente paciente;
	// bi-directional many-to-one association to FamiliarPaciente
	@ManyToOne
	@JoinColumn(name = "ID_FAMILIAR_PACIENTE")
	private FamiliarPaciente familiarPaciente;

	public PatologiaAsociada() {
	}

	public int getIdPatologiasAsociadas() {
		return this.idPatologiaAsociada;
	}

	public void setIdPatologiasAsociadas(int idPatologiasAsociadas) {
		this.idPatologiaAsociada = idPatologiasAsociadas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombrePatologia) {
		this.nombre = nombrePatologia;
	}

	public DatosMedico getDatosMedico() {
		return this.datosMedico;
	}

	public void setDatosMedico(DatosMedico datosMedico) {
		this.datosMedico = datosMedico;
	}

	public FamiliarPaciente getFamiliarPaciente() {
		return this.familiarPaciente;
	}

	public void setFamiliarPaciente(FamiliarPaciente familiarPaciente) {
		this.familiarPaciente = familiarPaciente;
	}

	public int getIdPatologiaAsociada() {
		return idPatologiaAsociada;
	}

	public void setIdPatologiaAsociada(int idPatologiaAsociada) {
		this.idPatologiaAsociada = idPatologiaAsociada;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente2) {
		this.paciente = paciente2;
	}

}