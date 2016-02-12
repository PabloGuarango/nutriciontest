package ec.edu.epn.nutricion.entities;


import javax.persistence.*;

/**
 * The persistent class for the patologia_asociada database table.
 * 
 */
@Entity
@Table(name = "patologia_asociada")
@NamedQuery(name = "PatologiaAsociada.findAll", query = "SELECT p FROM PatologiaAsociada p")
public class PatologiaAsociada extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PATOLOGIA_ASOCIADA")
	private int idPatologiaAsociada;

	@Column(name = "DESCRIPCION_PATOLOGIA")
	private String descripcion;

	@Column(name = "NOMBRE_PATOLOGIA")
	private String nombre;
	
	// Familiar con ANTECEDENTES PATOLÓGICOS del paciente
	@Column(name = "FAMILIAR")
	private String familiar;
	// bi-directional many-to-one association to InformacionMedica
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_INFORMACION_MEDICA")
	private InformacionMedica informacionMedica;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_PACIENTE")
	private Paciente paciente;

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

	public InformacionMedica getDatosMedico() {
		return this.informacionMedica;
	}

	public void setDatosMedico(InformacionMedica informacionMedica) {
		this.informacionMedica = informacionMedica;
	}

	public String getFamiliar() {
		return familiar;
	}

	public void setFamiliar(String familiar) {
		this.familiar = familiar;
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

	@Override
	public int getId() {
		return idPatologiaAsociada;
	}

}