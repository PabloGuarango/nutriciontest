package ec.edu.epn.nutricion.entities;

import javax.persistence.*;



/**
 * The persistent class for the cirugia database table.
 * 
 */
@Entity
@NamedQuery(name="Cirugia.findAll", query="SELECT c FROM Cirugia c")
public class Cirugia extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CIRUGIA")
	private int idCirugia;

	private String descripcion;

	//bi-directional many-to-one association to Paciente
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_PACIENTE")
	private Paciente paciente;

	public Cirugia() {
	}

	public int getIdCirugias() {
		return this.idCirugia;
	}

	public void setIdCirugias(int idCirugias) {
		this.idCirugia = idCirugias;
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

	@Override
	public int getId() {
		return this.idCirugia;
	}

}