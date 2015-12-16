package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the familiar_paciente database table.
 * 
 */
@Entity
@Table(name="familiar_paciente")
@NamedQuery(name="FamiliarPaciente.findAll", query="SELECT f FROM FamiliarPaciente f")
public class FamiliarPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FAMILIAR_PACIENTE")
	private int idFamiliarPaciente;

	@Column(name="PARENTEZCO_PACIENTE")
	private String parentezcoPaciente;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="ID_PACIENTE")
	private Paciente paciente;

	//bi-directional many-to-one association to PatologiaAsociada
	@OneToMany(mappedBy="familiarPaciente")
	private List<PatologiaAsociada> patologiaAsociadas;

	public FamiliarPaciente() {
	}

	public int getIdFamiliarPaciente() {
		return this.idFamiliarPaciente;
	}

	public void setIdFamiliarPaciente(int idFamiliarPaciente) {
		this.idFamiliarPaciente = idFamiliarPaciente;
	}

	public String getParentezcoPaciente() {
		return this.parentezcoPaciente;
	}

	public void setParentezcoPaciente(String parentezcoPaciente) {
		this.parentezcoPaciente = parentezcoPaciente;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<PatologiaAsociada> getPatologiaAsociadas() {
		return this.patologiaAsociadas;
	}

	public void setPatologiaAsociadas(List<PatologiaAsociada> patologiaAsociadas) {
		this.patologiaAsociadas = patologiaAsociadas;
	}

	public PatologiaAsociada addPatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		getPatologiaAsociadas().add(patologiaAsociada);
		patologiaAsociada.setFamiliarPaciente(this);

		return patologiaAsociada;
	}

	public PatologiaAsociada removePatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		getPatologiaAsociadas().remove(patologiaAsociada);
		patologiaAsociada.setFamiliarPaciente(null);

		return patologiaAsociada;
	}

}