package ec.edu.epn.nutricion.entities;


import javax.persistence.*;



/**
 * The persistent class for the problema_gastrointestinal database table.
 * 
 */
@Entity
@Table(name="problema_gastrointestinal")
@NamedQuery(name="ProblemaGastrointestinal.findAll", query="SELECT p FROM ProblemaGastrointestinal p")
public class ProblemaGastrointestinal extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROBLEMA_GASTROINTESTINAL")
	private int idProblemaGastrointestinal;

	private String descripcion;

	//bi-directional many-to-one association to AntecedenteAlimentario
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ANTECEDENTE_ALIMENTARIO")
	private AntecedenteAlimentario antecedenteAlimentario;

	public ProblemaGastrointestinal() {
	}

	public int getIdProblemasGastrointestinales() {
		return this.idProblemaGastrointestinal;
	}

	public void setIdProblemasGastrointestinales(int idProblemasGastrointestinales) {
		this.idProblemaGastrointestinal = idProblemasGastrointestinales;
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
		return idProblemaGastrointestinal;
	}

}