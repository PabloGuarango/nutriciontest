package ec.edu.epn.nutricion.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;



/**
 * The persistent class for the alimento database table.
 * 
 */
@Entity
@NamedQuery(name="Alimento.findAll", query="SELECT a FROM Alimento a")
public class Alimento extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ALIMENTO")
	private int idAlimento;

	@Column(name="DESCRIPCION_ALIMENTO")
	private String descripcionAlimento;

	@Column(name="NOMBRE_ALIMENTO")
	private String nombreAlimento;

	//bi-directional many-to-one association to AntecedenteAlimentario
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ANTECEDENTE_ALIMENTARIO")
	private AntecedenteAlimentario antecedenteAlimentario;

	public Alimento() {
	}

	public int getIdAlimentos() {
		return this.idAlimento;
	}

	public void setIdAlimentos(int idAlimentos) {
		this.idAlimento = idAlimentos;
	}

	public String getDescripcionAlimento() {
		return this.descripcionAlimento;
	}

	public void setDescripcionAlimento(String descripcionAlimento) {
		this.descripcionAlimento = descripcionAlimento;
	}

	public String getNombreAlimento() {
		return this.nombreAlimento;
	}

	public void setNombreAlimento(String nombreAlimento) {
		this.nombreAlimento = nombreAlimento;
	}

	public AntecedenteAlimentario getAntecedentesAlimentario() {
		return this.antecedenteAlimentario;
	}

	public void setAntecedentesAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		this.antecedenteAlimentario = antecedenteAlimentario;
	}

	@Override
	public int getId() {
		return this.idAlimento;
	}

}