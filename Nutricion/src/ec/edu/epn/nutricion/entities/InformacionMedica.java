package ec.edu.epn.nutricion.entities;

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
public class InformacionMedica extends EntidadBase {
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
	@OneToMany(mappedBy = "informacionMedica", cascade=CascadeType.ALL)
	private List<Antropometria> listaAntropometria;

	// bi-directional many-to-one association to Antropometria
	@ManyToOne
	@JoinColumn(name = "ID_ANTROPOMETRIA")
	private Antropometria antropometria;

	// bi-directional many-to-one association to HistoriaClinica
	@OneToMany(mappedBy = "informacionMedica", cascade=CascadeType.ALL)
	private List<HistoriaClinica> listaHistoriaClinicas;

	// bi-directional many-to-one association to Medicamento
	@OneToMany(mappedBy = "informacionMedica", cascade=CascadeType.ALL)
	private List<Medicamento> listaMedicamentos;

	// bi-directional many-to-one association to SuplementoNutricional
	@OneToMany(mappedBy = "informacionMedica", cascade=CascadeType.ALL)
	private List<SuplementoNutricional> listaSuplementoNutricionales;

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

	public List<Antropometria> getListaAntropometria() {
		return this.listaAntropometria;
	}

	public void setListaAntropometria(List<Antropometria> antropometrias) {
		this.listaAntropometria = antropometrias;
	}

	public Antropometria addAntropometria(Antropometria antropometria) {
		if(getListaAntropometria()==null)
			listaAntropometria=new ArrayList<Antropometria>();
		getListaAntropometria().add(antropometria);
		antropometria.setDatosMedico(this);

		return antropometria;
	}

	public Antropometria removeAntropometria(Antropometria antropometria) {
		if(getListaAntropometria()==null)
			return null;
		getListaAntropometria().remove(antropometria);
		antropometria.setDatosMedico(null);

		return antropometria;
	}

	public Antropometria getAntropometria() {
		return this.antropometria;
	}

	public void setAntropometria(Antropometria antropometria) {
		this.antropometria = antropometria;
	}
	public List<HistoriaClinica> getListaHistoriaClinicas() {
		return listaHistoriaClinicas;
	}
	public void setListaHistoriaClinicas(List<HistoriaClinica> listaHistoriaClinicas) {
		this.listaHistoriaClinicas = listaHistoriaClinicas;
	}
	public List<SuplementoNutricional> getListaSuplementoNutricionales() {
		return listaSuplementoNutricionales;
	}
	public void setListaSuplementoNutricionales(List<SuplementoNutricional> listaSuplementoNutricionales) {
		this.listaSuplementoNutricionales = listaSuplementoNutricionales;
	}
	public HistoriaClinica addHistoriaClinica(HistoriaClinica historiaClinica) {
		if(getListaHistoriaClinicas()==null)
			listaHistoriaClinicas=new ArrayList<HistoriaClinica>();
		getListaHistoriaClinicas().add(historiaClinica);
		historiaClinica.setDatosMedico(this);

		return historiaClinica;
	}

	public HistoriaClinica removeHistoriaClinica(HistoriaClinica historiaClinica) {
		if(getListaHistoriaClinicas()==null)
			return null;
		getListaHistoriaClinicas().remove(historiaClinica);
		historiaClinica.setDatosMedico(null);

		return historiaClinica;
	}

	public Medicamento addMedicamento(Medicamento medicamento) {
		if(getListaMedicamentos()==null)
			listaMedicamentos=new ArrayList<Medicamento>();
		getListaMedicamentos().add(medicamento);
		medicamento.setDatosMedico(this);

		return medicamento;
	}

	public Medicamento removeMedicamento(Medicamento medicamento) {
		if(getListaMedicamentos()==null)
			return null;
		getListaMedicamentos().remove(medicamento);
		medicamento.setDatosMedico(null);

		return medicamento;
	}
	public SuplementoNutricional addSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		if(getListaSuplementoNutricionales()==null)
			listaSuplementoNutricionales=new ArrayList<SuplementoNutricional>();
		getListaSuplementoNutricionales().add(suplementoNutricional);
		suplementoNutricional.setDatosMedico(this);

		return suplementoNutricional;
	}

	public SuplementoNutricional removeSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		if(getListaSuplementoNutricionales()==null)
			return null;
		getListaSuplementoNutricionales().remove(suplementoNutricional);
		suplementoNutricional.setDatosMedico(null);

		return suplementoNutricional;
	}

	public String getDxMedico() {
		return dxMedico;
	}

	public void setDxMedico(String dxMedico) {
		this.dxMedico = dxMedico;
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
	@Override
	public int getId() {
		return this.idInformacionMedica;
	}
}