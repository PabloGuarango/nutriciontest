package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the antecedente_salud database table.
 * 
 */
@Entity
@Table(name="antecedente_salud")
@NamedQuery(name="AntecedenteSalud.findAll", query="SELECT a FROM AntecedenteSalud a")
public class AntecedenteSalud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANTECEDENTE")
	private int idAntecedente;

	private String descripcion;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="ID_PACIENTE")
	private Paciente paciente;

	public AntecedenteSalud() {
	}

	public int getIdAntecedente() {
		return this.idAntecedente;
	}

	public void setIdAntecedente(int idAntecedente) {
		this.idAntecedente = idAntecedente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}