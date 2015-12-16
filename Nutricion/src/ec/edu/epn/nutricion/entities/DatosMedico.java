package ec.edu.epn.nutricion.entities;

import java.io.Serializable;

import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the datos_medicos database table.
 * 
 */
@Entity
@Table(name="datos_medicos")
@NamedQuery(name="DatosMedico.findAll", query="SELECT d FROM DatosMedico d")
public class DatosMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DATOS_MEDICOS")
	private int idDatosMedicos;

	@Column(name="EJERCICIO_TIPO")
	private String ejercicioTipo;
	@Column(name="MOTIVO_DE_CONSULTA")
	private String motivoDeConsulta;
	
	@Column(name="DX_MEDICO")
	private String dxMedico;
	@Column(name="EJERCICIO_VECES")
	private int ejercicioVeces;

	@Column(name="EJERICICIO_DURACION")
	private float ejercicioDuracion;

	@Column(name="REALIZA_EJERCICIO")
	private byte realizaEjercicio;

	//bi-directional many-to-one association to Antropometria
	@OneToMany(mappedBy="datosMedico")
	private List<Antropometria> listAntropometria;

	//bi-directional many-to-one association to Antropometria
	@ManyToOne
	@JoinColumn(name="ID_ANTROPOMETRIA")
	private Antropometria antropometria;

	//bi-directional many-to-one association to HistoriaClinica
	@OneToMany(mappedBy="datosMedico")
	private List<HistoriaClinica> historiaClinicas;

	//bi-directional many-to-one association to Medicamento
	@OneToMany(mappedBy="datosMedico")
	private List<Medicamento> medicamentos;

	
	//bi-directional many-to-one association to SuplementoNutricional
	@OneToMany(mappedBy="datosMedico")
	private List<SuplementoNutricional> suplementoNutricionals;

	public DatosMedico() {
	}

	public int getIdDatosMedicos() {
		return this.idDatosMedicos;
	}

	public void setIdDatosMedicos(int idDatosMedicos) {
		this.idDatosMedicos = idDatosMedicos;
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

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Medicamento addMedicamento(Medicamento medicamento) {
		getMedicamentos().add(medicamento);
		medicamento.setDatosMedico(this);

		return medicamento;
	}

	public Medicamento removeMedicamento(Medicamento medicamento) {
		getMedicamentos().remove(medicamento);
		medicamento.setDatosMedico(null);

		return medicamento;
	}


	public List<SuplementoNutricional> getSuplementoNutricionals() {
		return this.suplementoNutricionals;
	}

	public void setSuplementoNutricionals(List<SuplementoNutricional> suplementoNutricionals) {
		this.suplementoNutricionals = suplementoNutricionals;
	}

	public SuplementoNutricional addSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		getSuplementoNutricionals().add(suplementoNutricional);
		suplementoNutricional.setDatosMedico(this);

		return suplementoNutricional;
	}

	public SuplementoNutricional removeSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		getSuplementoNutricionals().remove(suplementoNutricional);
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
}