package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the medicamento database table.
 * 
 */
@Entity
@NamedQuery(name="Medicamento.findAll", query="SELECT m FROM Medicamento m")
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MEDICAMENTO")
	private int idMedicamento;

	private String descripcion;

	//bi-directional many-to-one association to InformacionMedica
	@ManyToOne
	@JoinColumn(name="ID_INFORMACION_MEDICA")
	private InformacionMedica informacionMedica;

	public Medicamento() {
	}

	public int getIdMedicamento() {
		return this.idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public InformacionMedica getDatosMedico() {
		return this.informacionMedica;
	}

	public void setDatosMedico(InformacionMedica informacionMedica) {
		this.informacionMedica = informacionMedica;
	}

}