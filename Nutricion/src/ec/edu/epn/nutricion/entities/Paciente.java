package ec.edu.epn.nutricion.entities;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
public class Paciente extends EntidadBase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PACIENTE")
	private int idPaciente;

	@Column(name = "APELLIDO")
	@NotNull(message="El campo apellidos es obligatorio")
	private String apellidos;

	@Email(message="Correo ingresado incorrecto. Ejemplo: username@ejemplo.com")
	@Column(name = "CORREO")
	private String correo;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NACIMIENTO")
	@NotNull(message="El campo fecha de nacimiento es obligatorio")
	private Date fechaNacimiento;

	@Column(name = "NOMBRE")
	@NotNull(message="El campo nombres es obligatorio")
	private String nombres;

	@Column(name = "ROL")
	@NotNull(message="El campo rol es obligatorio")
	private String rol;
	
	@Column(name = "numero_unico")
	private String numeroUnico;
	
	@Column(name = "SEXO")
	@NotNull(message="El campo sexo es obligatorio")
	private String sexo;

	@Column(name = "TELEFONO")
	private String telefono;

	@Column(name = "CEDULA_IDENTIDAD")
	@NotNull(message="El campo C.I. es obligatorio")
	private String cedulaIdentidad;

	// bi-directional many-to-one association to AntecedenteSalud
	@OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private List<AntecedenteSalud> listaAntecedentesSalud;

	// bi-directional many-to-one association to Cirugia
	@OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Cirugia> listaCirugias;

	// bi-directional many-to-one association to HistoriaClinica
	@OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<HistoriaClinica> listaHistoriasClinicas;

	// bi-directional many-to-one association to PatologiaAsociada
	@OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<PatologiaAsociada> listaPatologiasAsociadas;
	// bi-directional one-to-one association to AntecedenteAlimentario
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = CascadeType.ALL)
	private AntecedenteAlimentario antecedenteAlimentario;

	@Column(name = "edad")
	private int edad;
	public Paciente() {
	}

	public AntecedenteSalud addAntecedenteSalud(AntecedenteSalud antecedenteSalud) {
		if (getListaAntecedentesSalud() == null)
			listaAntecedentesSalud = new ArrayList<AntecedenteSalud>();
		getListaAntecedentesSalud().add(antecedenteSalud);
		antecedenteSalud.setPaciente(this);

		return antecedenteSalud;
	}

	public AntecedenteSalud removeAntecedenteSalud(AntecedenteSalud antecedenteSalud) {
		if (getListaAntecedentesSalud() == null)
			return null;
		getListaAntecedentesSalud().remove(antecedenteSalud);
		antecedenteSalud.setPaciente(null);

		return antecedenteSalud;
	}

	public PatologiaAsociada addPatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		if (getListaPatologiasAsociadas() == null)
			this.listaPatologiasAsociadas = new ArrayList<PatologiaAsociada>();
		getListaPatologiasAsociadas().add(patologiaAsociada);
		patologiaAsociada.setPaciente(this);
		return patologiaAsociada;
	}

	public PatologiaAsociada removePatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		if (getListaPatologiasAsociadas() == null)
			return null;
		getListaPatologiasAsociadas().remove(patologiaAsociada);
		patologiaAsociada.setPaciente(null);

		return patologiaAsociada;
	}

	public Cirugia addCirugia(Cirugia cirugia) {
		if (getListaCirugias() == null)
			listaCirugias = new ArrayList<Cirugia>();
		getListaCirugias().add(cirugia);
		cirugia.setPaciente(this);

		return cirugia;
	}

	public Cirugia removeCirugia(Cirugia cirugia) {
		if (getListaCirugias() == null)
			return null;
		getListaCirugias().remove(cirugia);
		cirugia.setPaciente(null);

		return cirugia;
	}

	public HistoriaClinica addHistoriaClinica(HistoriaClinica historiaClinica) {
		if (getListaHistoriasClinicas() == null)
			listaHistoriasClinicas = new ArrayList<HistoriaClinica>();
		getListaHistoriasClinicas().add(historiaClinica);
		historiaClinica.setPaciente(this);

		return historiaClinica;
	}

	public HistoriaClinica removeHistoriaClinica(HistoriaClinica historiaClinica) {
		if (getListaHistoriasClinicas() == null)
			return null;
		getListaHistoriasClinicas().remove(historiaClinica);
		historiaClinica.setPaciente(null);

		return historiaClinica;
	}
	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public void setApellido(String apellido) {
		this.apellidos = apellido;
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

	public void setNombre(String nombre) {
		this.nombres = nombre;
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
	@Override
	public String toString() {
		return getApellidos() + " " + getNombres();
	}

	public List<AntecedenteSalud> getListaAntecedentesSalud() {
		return listaAntecedentesSalud;
	}

	public void setListaAntecedentesSalud(List<AntecedenteSalud> listaAntecedentesSalud) {
		this.listaAntecedentesSalud = listaAntecedentesSalud;
	}

	public List<Cirugia> getListaCirugias() {
		return listaCirugias;
	}

	public void setListaCirugias(List<Cirugia> listaCirugias) {
		this.listaCirugias = listaCirugias;
	}

	public List<HistoriaClinica> getListaHistoriasClinicas() {
		return listaHistoriasClinicas;
	}

	public void setListaHistoriasClinicas(List<HistoriaClinica> listaHistoriasClinicas) {
		this.listaHistoriasClinicas = listaHistoriasClinicas;
	}

	public List<PatologiaAsociada> getListaPatologiasAsociadas() {
		return listaPatologiasAsociadas;
	}

	public void setListaPatologiasAsociadas(List<PatologiaAsociada> listaPatologiasAsociadas) {
		this.listaPatologiasAsociadas = listaPatologiasAsociadas;
	}

	public String getNumeroUnico() {
		return numeroUnico;
	}

	public void setNumeroUnico(String numeroUnico) {
		this.numeroUnico = numeroUnico;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Override
	public int getId() {
		return idPaciente;
	}
}