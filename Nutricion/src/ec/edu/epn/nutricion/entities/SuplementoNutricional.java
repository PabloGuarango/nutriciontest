package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;



/**
 * The persistent class for the suplemento_nutricional database table.
 * 
 */
@Entity
@Table(name="suplemento_nutricional")
@NamedQuery(name="SuplementoNutricional.findAll", query="SELECT s FROM SuplementoNutricional s")
public class SuplementoNutricional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUPLEMENTO_NUTRICIONAL")
	private int idSuplementoNutricional;

	private String descripcion;

	//bi-directional many-to-one association to DatosMedico
	@ManyToOne
	@JoinColumn(name="ID_DATOS_MEDICOS")
	private DatosMedico datosMedico;

	public SuplementoNutricional() {
	}

	public int getIdSuplementoNutricional() {
		return this.idSuplementoNutricional;
	}

	public void setIdSuplementoNutricional(int idSuplementoNutricional) {
		this.idSuplementoNutricional = idSuplementoNutricional;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public DatosMedico getDatosMedico() {
		return this.datosMedico;
	}

	public void setDatosMedico(DatosMedico datosMedico) {
		this.datosMedico = datosMedico;
	}

}