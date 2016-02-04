package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the datos_medicos database table.
 * 
 */
@Entity
@Table(name = "informacion_medica")
@NamedQuery(name = "InformacionMedica.findAll", query = "SELECT d FROM InformacionMedica d")
public class InformacionMedica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INFORMACION_MEDICA")
	private int idInformacionMedica;

	@Column(name = "EJERCICIO_TIPO")
	private String ejercicioTipo;
	@Column(name = "MOTIVO_DE_CONSULTA")
	private String motivoDeConsulta;

	@Column(name = "DX_MEDICO")
	private String dxMedico;
	@Column(name = "EJERCICIO_VECES")
	private int ejercicioVeces;

	@Column(name = "EJERICICIO_DURACION")
	private float ejercicioDuracion;

	@Column(name = "REALIZA_EJERCICIO")
	private byte realizaEjercicio;

	// bi-directional many-to-one association to Antropometria
	@OneToMany(mappedBy = "informacionMedica")
	private List<Antropometria> listAntropometria;

	// bi-directional many-to-one association to Antropometria
	@ManyToOne
	@JoinColumn(name = "ID_ANTROPOMETRIA")
	private Antropometria antropometria;

	// bi-directional many-to-one association to HistoriaClinica
	@OneToMany(mappedBy = "informacionMedica")
	private List<HistoriaClinica> historiaClinicas;

	// bi-directional many-to-one association to Medicamento
	@OneToMany(mappedBy = "informacionMedica")
	private List<Medicamento> listaMedicamentos;

	// bi-directional many-to-one association to SuplementoNutricional
	@OneToMany(mappedBy = "informacionMedica")
	private List<SuplementoNutricional> listaSuplementoNutricional;

	public InformacionMedica() {
	}
	public int getIdInformacionMedica() {
		return idInformacionMedica;
	}
	public void setIdInformacionMedica(int idInformacionMedica) {
		this.idInformacionMedica = idInformacionMedica;
	}
	public String getEjercicioTipo() {
		return this.ejercicioTipo;
	}

	public void setEjercicioTipo(String ejercicioTipo) {
		this.ejercicioTipo = ejercicioTipo;
	}

	public int getEjercicioVeces() {
		return this.ejercicioVeces;
	}

	public void setEjercicioVeces(int ejercicioVeces) {
		this.ejercicioVeces = ejercicioVeces;
	}

	public float getEjercicioDuracion() {
		return this.ejercicioDuracion;
	}

	public void setEjercicioDuracion(float ejericicioDuracion) {
		this.ejercicioDuracion = ejericicioDuracion;
	}

	public byte getRealizaEjercicio() {
		return this.realizaEjercicio;
	}

	public void setRealizaEjercicio(byte realizaEjercicio) {
		this.realizaEjercicio = realizaEjercicio;
	}

	public List<Antropometria> getAntropometrias() {
		if (this.listAntropometria == null)
			this.listAntropometria = new ArrayList<Antropometria>();
		return this.listAntropometria;
	}

	public void setAntropometrias(List<Antropometria> antropometrias) {
		this.listAntropometria = antropometrias;
	}

	public Antropometria addAntropometria(Antropometria antropometria) {
		getAntropometrias().add(antropometria);
		antropometria.setDatosMedico(this);

		return antropometria;
	}

	public Antropometria removeAntropometria(Antropometria antropometria) {
		getAntropometrias().remove(antropometria);
		antropometria.setDatosMedico(null);

		return antropometria;
	}

	public Antropometria getAntropometria() {
		return this.antropometria;
	}

	public void setAntropometria(Antropometria antropometria) {
		this.antropometria = antropometria;
	}

	public List<HistoriaClinica> getHistoriaClinicas() {
		if (this.historiaClinicas == null)
			this.historiaClinicas = new ArrayList<HistoriaClinica>();
		return this.historiaClinicas;
	}

	public void setHistoriaClinicas(List<HistoriaClinica> historiaClinicas) {
		this.historiaClinicas = historiaClinicas;
	}

	public HistoriaClinica addHistoriaClinica(HistoriaClinica historiaClinica) {
		getHistoriaClinicas().add(historiaClinica);
		historiaClinica.setDatosMedico(this);

		return historiaClinica;
	}

	public HistoriaClinica removeHistoriaClinica(HistoriaClinica historiaClinica) {
		getHistoriaClinicas().remove(historiaClinica);
		historiaClinica.setDatosMedico(null);

		return historiaClinica;
	}

	public Medicamento addMedicamento(Medicamento medicamento) {
		getListaMedicamentos().add(medicamento);
		medicamento.setDatosMedico(this);

		return medicamento;
	}

	public Medicamento removeMedicamento(Medicamento medicamento) {
		getListaMedicamentos().remove(medicamento);
		medicamento.setDatosMedico(null);

		return medicamento;
	}
	public SuplementoNutricional addSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		getListaSuplementoNutricional().add(suplementoNutricional);
		suplementoNutricional.setDatosMedico(this);

		return suplementoNutricional;
	}

	public SuplementoNutricional removeSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		getListaSuplementoNutricional().remove(suplementoNutricional);
		suplementoNutricional.setDatosMedico(null);

		return suplementoNutricional;
	}

	public String getDxMedico() {
		return dxMedico;
	}

	public void setDxMedico(String dxMedico) {
		this.dxMedico = dxMedico;
	}

	public List<Antropometria> getListAntropometria() {
		if (this.listAntropometria == null)
			listAntropometria = new ArrayList<Antropometria>();
		return listAntropometria;
	}

	public void setListAntropometria(List<Antropometria> listAntropometria) {
		this.listAntropometria = listAntropometria;
	}

	public String getMotivoDeConsulta() {
		return motivoDeConsulta;
	}

	public void setMotivoDeConsulta(String motivoDeConsulta) {
		this.motivoDeConsulta = motivoDeConsulta;
	}

	public List<Medicamento> getListaMedicamentos() {
		return listaMedicamentos;
	}

	public void setListaMedicamentos(List<Medicamento> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

	public List<SuplementoNutricional> getListaSuplementoNutricional() {
		return listaSuplementoNutricional;
	}

	public void setListaSuplementoNutricional(List<SuplementoNutricional> listaSuplementoNutricional) {
		this.listaSuplementoNutricional = listaSuplementoNutricional;
	}
}