package ec.edu.epn.nutricion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

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
	
	@Email
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
	@OneToMany(mappedBy = "paciente", fetch=FetchType.EAGER)
	private List<AntecedenteSalud> listaAntecedenteSaluds;

	// bi-directional many-to-one association to Cirugia
	@OneToMany(mappedBy = "paciente", fetch=FetchType.EAGER)
	private List<Cirugia> listaCirugias;

	// bi-directional many-to-one association to HistoriaClinica
	@OneToMany(mappedBy = "paciente", fetch=FetchType.EAGER)
	private List<HistoriaClinica> listaHistoriaClinicas;

	// bi-directional many-to-one association to PatologiaAsociada
	@OneToMany(mappedBy = "paciente", fetch=FetchType.EAGER)
	private List<PatologiaAsociada> listaPatologiaAsociadas;
	// bi-directional one-to-one association to AntecedenteAlimentario
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = CascadeType.ALL)
	private AntecedenteAlimentario antecedenteAlimentario;

	@Column(name = "edad")
	private int edad;
	public Paciente() {
	}

	public AntecedenteSalud addAntecedenteSalud(AntecedenteSalud antecedenteSalud) {
		getListaAntecedenteSaluds().add(antecedenteSalud);
		antecedenteSalud.setPaciente(this);

		return antecedenteSalud;
	}

	public AntecedenteSalud removeAntecedenteSalud(AntecedenteSalud antecedenteSalud) {
		getListaAntecedenteSaluds().remove(antecedenteSalud);
		antecedenteSalud.setPaciente(null);

		return antecedenteSalud;
	}

	public PatologiaAsociada addPatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		if (getListaPatologiaAsociadas() != null) {
			getListaPatologiaAsociadas().add(patologiaAsociada);
			patologiaAsociada.setPaciente(this);
		} else {
			this.listaPatologiaAsociadas = new ArrayList<PatologiaAsociada>();
			getListaPatologiaAsociadas().add(patologiaAsociada);
			patologiaAsociada.setPaciente(this);
		}

		return patologiaAsociada;
	}

	public PatologiaAsociada removePatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		getListaPatologiaAsociadas().remove(patologiaAsociada);
		patologiaAsociada.setPaciente(null);

		return patologiaAsociada;
	}

	public Cirugia addCirugia(Cirugia cirugia) {
		getListaCirugias().add(cirugia);
		cirugia.setPaciente(this);

		return cirugia;
	}

	public Cirugia removeCirugia(Cirugia cirugia) {
		getListaCirugias().remove(cirugia);
		cirugia.setPaciente(null);

		return cirugia;
	}

	public HistoriaClinica addHistoriaClinica(HistoriaClinica historiaClinica) {
		getListaHistoriaClinicas().add(historiaClinica);
		historiaClinica.setPaciente(this);

		return historiaClinica;
	}

	public HistoriaClinica removeHistoriaClinica(HistoriaClinica historiaClinica) {
		getListaHistoriaClinicas().remove(historiaClinica);
		historiaClinica.setPaciente(null);

		return historiaClinica;
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

	public List<AntecedenteSalud> getListaAntecedenteSaluds() {
		return listaAntecedenteSaluds;
	}

	public void setListaAntecedenteSaluds(List<AntecedenteSalud> listaAntecedenteSaluds) {
		this.listaAntecedenteSaluds = listaAntecedenteSaluds;
	}

	public List<Cirugia> getListaCirugias() {
		return listaCirugias;
	}

	public void setListaCirugias(List<Cirugia> listaCirugias) {
		this.listaCirugias = listaCirugias;
	}

	public List<HistoriaClinica> getListaHistoriaClinicas() {
		return listaHistoriaClinicas;
	}

	public void setListaHistoriaClinicas(List<HistoriaClinica> listaHistoriaClinicas) {
		this.listaHistoriaClinicas = listaHistoriaClinicas;
	}

	public List<PatologiaAsociada> getListaPatologiaAsociadas() {
		return listaPatologiaAsociadas;
	}

	public void setListaPatologiaAsociadas(List<PatologiaAsociada> listaPatologiaAsociadas) {
		this.listaPatologiaAsociadas = listaPatologiaAsociadas;
	}

	@Override
	public String toString() {
		return getApellido()+" "+getNombre();
	}

}