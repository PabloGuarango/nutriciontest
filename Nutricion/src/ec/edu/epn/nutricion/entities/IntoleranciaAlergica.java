package ec.edu.epn.nutricion.entities;

import javax.persistence.*;



/**
 * The persistent class for the intolerancia_alergica database table.
 * 
 */
@Entity
@Table(name="intolerancia_alergica")
@NamedQuery(name="IntoleranciaAlergica.findAll", query="SELECT i FROM IntoleranciaAlergica i")
public class IntoleranciaAlergica extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INTOLERANCIA_ALERGICA")
	private int idIntoleranciaAlergica;

	private String descripcion;

	//bi-directional many-to-one association to AntecedenteAlimentario
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ANTECEDENTES_ALIMENTARIOS")
	private AntecedenteAlimentario antecedenteAlimentario;

	public IntoleranciaAlergica() {
	}

	public int getIdIntoleranciasAlergicas() {
		return this.idIntoleranciaAlergica;
	}

	public void setIdIntoleranciasAlergicas(int idIntoleranciasAlergicas) {
		this.idIntoleranciaAlergica = idIntoleranciasAlergicas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public AntecedenteAlimentario getAntecedentesAlimentario() {
		return this.antecedenteAlimentario;
	}

	public void setAntecedentesAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		this.antecedenteAlimentario = antecedenteAlimentario;
	}

	@Override
	public int getId() {
		return idIntoleranciaAlergica;
	}

}