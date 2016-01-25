package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PACIENTE")
	private int idPaciente;

	@Column(name = "APELLIDO")
	private String apellido;

	@Column(name = "CORREO")
	private String correo;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "ROL")
	private String rol;

	@Column(name = "SEXO")
	private String sexo;

	@Column(name = "TELEFONO")
	private String telefono;

	@Column(name = "CEDULA_IDENTIDAD")
	private String cedulaIdentidad;

	// bi-directional many-to-one association to AntecedenteSalud
	@OneToMany(mappedBy = "paciente")
	private List<AntecedenteSalud> antecedenteSaluds;

	// bi-directional many-to-one association to AntecedenteAlimentario
	@OneToMany(mappedBy = "paciente")
	private List<AntecedenteAlimentario> antecedenteAlimentarios;

	// bi-directional many-to-one association to Cirugia
	@OneToMany(mappedBy = "paciente")
	private List<Cirugia> cirugias;

	// bi-directional many-to-one association to HistoriaClinica
	@OneToMany(mappedBy = "paciente")
	private List<HistoriaClinica> historiaClinicas;

	// bi-directional many-to-one association to AntecedenteAlimentario
	@ManyToOne
	@JoinColumn(name = "ID_ANTECEDENTES_ALIMENTARIOS")
	private AntecedenteAlimentario antecedenteAlimentario;

	// bi-directional many-to-one association to PatologiaAsociada
	@OneToMany(mappedBy = "paciente")
	private List<PatologiaAsociada> patologiaAsociadas;

	@Transient
	private int edad;
	public Paciente() {
	}

	public List<AntecedenteSalud> getAntecedenteSaluds() {
		return this.antecedenteSaluds;
	}

	public void setAntecedenteSaluds(List<AntecedenteSalud> antecedenteSaluds) {
		this.antecedenteSaluds = antecedenteSaluds;
	}

	public List<PatologiaAsociada> getPatologiaAsociadas() {
		return patologiaAsociadas;
	}

	public void setPatologiaAsociadas(List<PatologiaAsociada> patologiaAsociadas) {
		this.patologiaAsociadas = patologiaAsociadas;
	}

	public AntecedenteSalud addAntecedenteSalud(AntecedenteSalud antecedenteSalud) {
		getAntecedenteSaluds().add(antecedenteSalud);
		antecedenteSalud.setPaciente(this);

		return antecedenteSalud;
	}

	public AntecedenteSalud removeAntecedenteSalud(AntecedenteSalud antecedenteSalud) {
		getAntecedenteSaluds().remove(antecedenteSalud);
		antecedenteSalud.setPaciente(null);

		return antecedenteSalud;
	}

	public List<AntecedenteAlimentario> getAntecedentesAlimentarios() {
		return this.antecedenteAlimentarios;
	}

	public void setAntecedentesAlimentarios(List<AntecedenteAlimentario> antecedenteAlimentarios) {
		this.antecedenteAlimentarios = antecedenteAlimentarios;
	}

	public PatologiaAsociada addPatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		if (getPatologiaAsociadas() != null) {
			getPatologiaAsociadas().add(patologiaAsociada);
			patologiaAsociada.setPaciente(this);
		} else {
			this.patologiaAsociadas = new ArrayList<PatologiaAsociada>();
			getPatologiaAsociadas().add(patologiaAsociada);
			patologiaAsociada.setPaciente(this);
		}

		return patologiaAsociada;
	}

	public PatologiaAsociada removePatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		getPatologiaAsociadas().remove(patologiaAsociada);
		patologiaAsociada.setPaciente(null);

		return patologiaAsociada;
	}

	public AntecedenteAlimentario addAntecedentesAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		getAntecedentesAlimentarios().add(antecedenteAlimentario);
		antecedenteAlimentario.setPaciente(this);

		return antecedenteAlimentario;
	}

	public AntecedenteAlimentario removeAntecedentesAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		getAntecedentesAlimentarios().remove(antecedenteAlimentario);
		antecedenteAlimentario.setPaciente(null);

		return antecedenteAlimentario;
	}

	public List<Cirugia> getCirugias() {
		return this.cirugias;
	}

	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}

	public Cirugia addCirugia(Cirugia cirugia) {
		getCirugias().add(cirugia);
		cirugia.setPaciente(this);

		return cirugia;
	}

	public Cirugia removeCirugia(Cirugia cirugia) {
		getCirugias().remove(cirugia);
		cirugia.setPaciente(null);

		return cirugia;
	}
	public List<HistoriaClinica> getHistoriaClinicas() {
		return this.historiaClinicas;
	}

	public void setHistoriaClinicas(List<HistoriaClinica> historiaClinicas) {
		this.historiaClinicas = historiaClinicas;
	}

	public HistoriaClinica addHistoriaClinica(HistoriaClinica historiaClinica) {
		getHistoriaClinicas().add(historiaClinica);
		historiaClinica.setPaciente(this);

		return historiaClinica;
	}

	public HistoriaClinica removeHistoriaClinica(HistoriaClinica historiaClinica) {
		getHistoriaClinicas().remove(historiaClinica);
		historiaClinica.setPaciente(null);

		return historiaClinica;
	}

	public AntecedenteAlimentario getAntecedentesAlimentario() {
		return this.antecedenteAlimentario;
	}

	public void setAntecedentesAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		this.antecedenteAlimentario = antecedenteAlimentario;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setCedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
	}

	public List<AntecedenteAlimentario> getAntecedenteAlimentarios() {
		return antecedenteAlimentarios;
	}

	public void setAntecedenteAlimentarios(List<AntecedenteAlimentario> antecedenteAlimentarios) {
		this.antecedenteAlimentarios = antecedenteAlimentarios;
	}

	public AntecedenteAlimentario getAntecedenteAlimentario() {
		return antecedenteAlimentario;
	}

	public void setAntecedenteAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		this.antecedenteAlimentario = antecedenteAlimentario;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}