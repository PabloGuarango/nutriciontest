package ec.edu.epn.nutricion.entities;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the historia_clinica database table.
 * 
 */
@Entity
@Table(name="historia_clinica")
@NamedQuery(name="HistoriaClinica.findAll", query="SELECT h FROM HistoriaClinica h")
public class HistoriaClinica extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_HISTORIA_CLINICA")
	private int idHistoriaClinica;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to InformacionMedica
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_INFORMACION_MEDICA")
	private InformacionMedica informacionMedica;

	//bi-directional many-to-one association to Paciente
	@ManyToOne(cascade=CascadeType.ALL)
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

	public InformacionMedica getDatosMedico() {
		return this.informacionMedica;
	}

	public void setDatosMedico(InformacionMedica informacionMedica) {
		this.informacionMedica = informacionMedica;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int getId() {
		return this.idHistoriaClinica;
	}

}