package ec.edu.epn.nutricion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.datatable.DataTable;

import ec.edu.epn.nutricion.entities.Alimento;
import ec.edu.epn.nutricion.entities.AntecedenteAlimentario;
import ec.edu.epn.nutricion.entities.Antropometria;
import ec.edu.epn.nutricion.entities.Cirugia;
import ec.edu.epn.nutricion.entities.DatosMedico;
import ec.edu.epn.nutricion.entities.FamiliarPaciente;
import ec.edu.epn.nutricion.entities.HistoriaClinica;
import ec.edu.epn.nutricion.entities.IntoleranciaAlergica;
import ec.edu.epn.nutricion.entities.Medicamento;
import ec.edu.epn.nutricion.entities.Paciente;
import ec.edu.epn.nutricion.entities.PatologiaAsociada;
import ec.edu.epn.nutricion.entities.ProblemaGastrointestinal;
import ec.edu.epn.nutricion.entities.SuplementoNutricional;
import ec.edu.epn.nutricion.model.servicio.ServicioGenerico;

@ManagedBean
@SessionScoped
public class PacienteBean {
	@EJB
	private ServicioGenerico<Paciente> servicioPaciente;
	@EJB
	private ServicioGenerico<Alimento> servicioAlimento;
	@EJB
	private ServicioGenerico<AntecedenteAlimentario> servicioAntecedenteAlimentario;
	@EJB
	private ServicioGenerico<Antropometria> servicioAntropometria;
	@EJB
	private ServicioGenerico<Cirugia> servicioCirugia;
	@EJB
	private ServicioGenerico<DatosMedico> servicioDatosMedico;
	@EJB
	private ServicioGenerico<FamiliarPaciente> servicioFamiliarPaciente;
	@EJB
	private ServicioGenerico<HistoriaClinica> servicioHistoriaClinica;
	@EJB
	private ServicioGenerico<IntoleranciaAlergica> servicioIntoleranciaAlergia;
	@EJB
	private ServicioGenerico<Medicamento> servicioMedicamento;
	@EJB
	private ServicioGenerico<PatologiaAsociada> servicioPatologiaAsociada;
	@EJB
	private ServicioGenerico<ProblemaGastrointestinal> servicioProblemaGastrointestinal;
	@EJB
	private ServicioGenerico<SuplementoNutricional> servicioSuplementoNutricional;
	private Paciente paciente;
	private Alimento alimento;
	private AntecedenteAlimentario antecedenteAlimentario;
	private Antropometria antropometria;
	private Cirugia cirugia;
	private DatosMedico datosMedicos;
	private FamiliarPaciente familiarPaciente;
	private HistoriaClinica historiaClinica;
	private IntoleranciaAlergica intoleranciaAlergia;
	private Medicamento medicamento;
	private PatologiaAsociada patologiaAsociada;
	private ProblemaGastrointestinal problemaGastrointestinal;
	private SuplementoNutricional suplementoNutricional;

	private List<Paciente> listaPaciente;
	private List<Alimento> listaAlimento;
	private List<Alimento> listaAlimentoNoPreferido;
	private List<Alimento> listaRefrigerios;
	private List<AntecedenteAlimentario> listaAntecedenteAlimentario;
	private List<Antropometria> listaAntropometria;
	private List<Cirugia> listaCirugia;
	private List<DatosMedico> listaDatosMedicos;
	private List<FamiliarPaciente> listaFamiliarPaciente;
	private List<HistoriaClinica> listaHistoriaClinica;
	private List<IntoleranciaAlergica> listaIntoleranciaAlergia;
	private List<Medicamento> listaMedicamento;
	private List<PatologiaAsociada> listaPatologiaAsociada;
	private List<ProblemaGastrointestinal> listaProblemaGastrointestinal;
	private List<SuplementoNutricional> listaSuplementoNutricional;

	private int edad;
	private boolean nuevo = true;
	private String ejercicio = "false";
	private String entrecomidas = "false";
	private String tabaco;
	private String alcohol;
	private String bebidasAzucaradas;
	private String cirugias = "false";
	private String suplementos= "false";
	private DataTable dtPatologia;
	private DataTable dtAlimento;
	private DataTable dtAlimentoNoPreferido;
	private DataTable dtRefrigerio;
	private DataTable dtCirugia;
	private DataTable dtMedicamento;
	private DataTable dtSuplemento;
	private DataTable dtHistoriaClinica;
	@PostConstruct
	public void init() {
		paciente = new Paciente();
		listaAlimento = new ArrayList<Alimento>();
		listaAlimentoNoPreferido = new ArrayList<Alimento>();
		listaRefrigerios = new ArrayList<Alimento>();
		listaCirugia = new ArrayList<Cirugia>();
		listaSuplementoNutricional= new ArrayList<SuplementoNutricional>();
		listaMedicamento= new ArrayList<Medicamento>();
		listaPatologiaAsociada = servicioPatologiaAsociada.obtenerListaCombo("PatologiaAsociada");
	}
	public String eliminarSuplemento() {
		SuplementoNutricional su = (SuplementoNutricional) dtSuplemento.getRowData();
		listaSuplementoNutricional.remove(su);
		return null;
	}
	public String eliminarMedicamento() {
		Medicamento al = (Medicamento) dtMedicamento.getRowData();
		listaMedicamento.remove(al);
		return null;
	}
	public String eliminarCirugia() {
		Cirugia al = (Cirugia) dtCirugia.getRowData();
		listaCirugia.remove(al);
		return null;
	}

	public String eliminarAlimento() {
		Alimento al = (Alimento) dtAlimento.getRowData();
		listaAlimento.remove(al);
		return null;
	}

	public String eliminarRefrigerio() {
		Alimento al = (Alimento) dtRefrigerio.getRowData();
		listaRefrigerios.remove(al);
		return null;
	}

	public String eliminarAlimentoNoPreferido() {
		Alimento al = (Alimento) dtAlimentoNoPreferido.getRowData();
		listaAlimentoNoPreferido.remove(al);
		return null;
	}
	public String agregarSuplemento() {
		SuplementoNutricional su = new SuplementoNutricional();
		listaSuplementoNutricional.add(su);
		return null;
	}
	public String agregarMedicamento() {
		Medicamento patologiaAsoc = new Medicamento();
		listaMedicamento.add(patologiaAsoc);
		return null;
	}
	public String agregarCirugia() {
		Cirugia patologiaAsoc = new Cirugia();
		listaCirugia.add(patologiaAsoc);
		return null;
	}

	public String agregarPatologia() {
		PatologiaAsociada patologiaAsoc = new PatologiaAsociada();
		paciente.addPatologiaAsociada(patologiaAsoc);
		return null;
	}

	public String agregarAlimento() {
		Alimento alimen = new Alimento();
		alimen.setDescripcionAlimento("preferido");
		listaAlimento.add(alimen);
		return null;
	}

	public String agregarRefrigerio() {
		Alimento alimen = new Alimento();
		alimen.setDescripcionAlimento("refrigerio");
		listaRefrigerios.add(alimen);
		return null;
	}

	public String agregarAlimentoNoPreferido() {
		Alimento alimen = new Alimento();
		alimen.setDescripcionAlimento("no preferido");
		listaAlimentoNoPreferido.add(alimen);
		return null;
	}

	public String eliminarPatologia() {
		PatologiaAsociada pt = (PatologiaAsociada) dtPatologia.getRowData();
		paciente.removePatologiaAsociada(pt);
		return null;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public AntecedenteAlimentario getAntecedenteAlimentario() {
		return antecedenteAlimentario;
	}

	public void setAntecedenteAlimentario(AntecedenteAlimentario antecedenteAlimentario) {
		this.antecedenteAlimentario = antecedenteAlimentario;
	}

	public Antropometria getAntropometria() {
		return antropometria;
	}

	public void setAntropometria(Antropometria antropometria) {
		this.antropometria = antropometria;
	}

	public Cirugia getCirugia() {
		return cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}

	public DatosMedico getDatosMedicos() {
		return datosMedicos;
	}

	public void setDatosMedicos(DatosMedico datosMedicos) {
		this.datosMedicos = datosMedicos;
	}

	public FamiliarPaciente getFamiliarPaciente() {
		return familiarPaciente;
	}

	public void setFamiliarPaciente(FamiliarPaciente familiarPaciente) {
		this.familiarPaciente = familiarPaciente;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public IntoleranciaAlergica getIntoleranciaAlergia() {
		return intoleranciaAlergia;
	}

	public void setIntoleranciaAlergia(IntoleranciaAlergica intoleranciaAlergia) {
		this.intoleranciaAlergia = intoleranciaAlergia;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public PatologiaAsociada getPatologiaAsociada() {
		return patologiaAsociada;
	}

	public void setPatologiaAsociada(PatologiaAsociada patologiaAsociada) {
		this.patologiaAsociada = patologiaAsociada;
	}

	public ProblemaGastrointestinal getProblemaGastrointestinal() {
		return problemaGastrointestinal;
	}

	public void setProblemaGastrointestinal(ProblemaGastrointestinal problemaGastrointestinal) {
		this.problemaGastrointestinal = problemaGastrointestinal;
	}

	public SuplementoNutricional getSuplementoNutricional() {
		return suplementoNutricional;
	}

	public void setSuplementoNutricional(SuplementoNutricional suplementoNutricional) {
		this.suplementoNutricional = suplementoNutricional;
	}

	public List<Paciente> getListaPaciente() {
		return listaPaciente;
	}

	public void setListaPaciente(List<Paciente> listaPaciente) {
		this.listaPaciente = listaPaciente;
	}

	public List<Alimento> getListaAlimento() {
		return listaAlimento;
	}

	public void setListaAlimento(List<Alimento> listaAlimento) {
		this.listaAlimento = listaAlimento;
	}

	public List<AntecedenteAlimentario> getListaAntecedenteAlimentario() {
		return listaAntecedenteAlimentario;
	}

	public void setListaAntecedenteAlimentario(List<AntecedenteAlimentario> listaAntecedenteAlimentario) {
		this.listaAntecedenteAlimentario = listaAntecedenteAlimentario;
	}

	public List<Antropometria> getListaAntropometria() {
		return listaAntropometria;
	}

	public void setListaAntropometria(List<Antropometria> listaAntropometria) {
		this.listaAntropometria = listaAntropometria;
	}

	public List<Cirugia> getListaCirugia() {
		return listaCirugia;
	}

	public void setListaCirugia(List<Cirugia> listaCirugia) {
		this.listaCirugia = listaCirugia;
	}

	public List<DatosMedico> getListaDatosMedicos() {
		return listaDatosMedicos;
	}

	public void setListaDatosMedicos(List<DatosMedico> listaDatosMedicos) {
		this.listaDatosMedicos = listaDatosMedicos;
	}

	public List<FamiliarPaciente> getListaFamiliarPaciente() {
		return listaFamiliarPaciente;
	}

	public void setListaFamiliarPaciente(List<FamiliarPaciente> listaFamiliarPaciente) {
		this.listaFamiliarPaciente = listaFamiliarPaciente;
	}

	public List<HistoriaClinica> getListaHistoriaClinica() {
		return listaHistoriaClinica;
	}

	public void setListaHistoriaClinica(List<HistoriaClinica> listaHistoriaClinica) {
		this.listaHistoriaClinica = listaHistoriaClinica;
	}

	public List<IntoleranciaAlergica> getListaIntoleranciaAlergia() {
		return listaIntoleranciaAlergia;
	}

	public void setListaIntoleranciaAlergia(List<IntoleranciaAlergica> listaIntoleranciaAlergia) {
		this.listaIntoleranciaAlergia = listaIntoleranciaAlergia;
	}

	public List<Medicamento> getListaMedicamento() {
		return listaMedicamento;
	}

	public void setListaMedicamento(List<Medicamento> listaMedicamento) {
		this.listaMedicamento = listaMedicamento;
	}

	public List<PatologiaAsociada> getListaPatologiaAsociada() {
		return listaPatologiaAsociada;
	}

	public void setListaPatologiaAsociada(List<PatologiaAsociada> listaPatologiaAsociada) {
		this.listaPatologiaAsociada = listaPatologiaAsociada;
	}

	public List<ProblemaGastrointestinal> getListaProblemaGastrointestinal() {
		return listaProblemaGastrointestinal;
	}

	public void setListaProblemaGastrointestinal(List<ProblemaGastrointestinal> listaProblemaGastrointestinal) {
		this.listaProblemaGastrointestinal = listaProblemaGastrointestinal;
	}

	public List<SuplementoNutricional> getListaSuplementoNutricional() {
		return listaSuplementoNutricional;
	}

	public void setListaSuplementoNutricional(List<SuplementoNutricional> listaSuplementoNutricional) {
		this.listaSuplementoNutricional = listaSuplementoNutricional;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public String getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	public String getEntrecomidas() {
		return entrecomidas;
	}

	public void setEntrecomidas(String entrecomidas) {
		this.entrecomidas = entrecomidas;
	}
	public DataTable getDtPatologia() {
		return dtPatologia;
	}

	public void setDtPatologia(DataTable dtPatologia) {
		this.dtPatologia = dtPatologia;
	}

	public DataTable getDtAlimento() {
		return dtAlimento;
	}

	public void setDtAlimento(DataTable dtAlimento) {
		this.dtAlimento = dtAlimento;
	}

	public List<Alimento> getListaAlimentoNoPreferido() {
		return listaAlimentoNoPreferido;
	}

	public void setListaAlimentoNoPreferido(List<Alimento> listaAlimentoNoPreferido) {
		this.listaAlimentoNoPreferido = listaAlimentoNoPreferido;
	}

	public DataTable getDtAlimentoNoPreferido() {
		return dtAlimentoNoPreferido;
	}

	public void setDtAlimentoNoPreferido(DataTable dtAlimentoNoPreferido) {
		this.dtAlimentoNoPreferido = dtAlimentoNoPreferido;
	}

	public String getTabaco() {
		return tabaco;
	}

	public void setTabaco(String tabaco) {
		this.tabaco = tabaco;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public String getBebidasAzucaradas() {
		return bebidasAzucaradas;
	}

	public void setBebidasAzucaradas(String bebidasAzucaradas) {
		this.bebidasAzucaradas = bebidasAzucaradas;
	}

	public List<Alimento> getListaRefrigerios() {
		return listaRefrigerios;
	}

	public void setListaRefrigerios(List<Alimento> listaRefrigerios) {
		this.listaRefrigerios = listaRefrigerios;
	}

	public DataTable getDtRefrigerio() {
		return dtRefrigerio;
	}

	public void setDtRefrigerio(DataTable dtRefrigerio) {
		this.dtRefrigerio = dtRefrigerio;
	}

	public String getCirugias() {
		return cirugias;
	}

	public void setCirugias(String cirugias) {
		this.cirugias = cirugias;
	}

	public DataTable getDtCirugia() {
		return dtCirugia;
	}

	public void setDtCirugia(DataTable dtCirugia) {
		this.dtCirugia = dtCirugia;
	}
	public DataTable getDtMedicamento() {
		return dtMedicamento;
	}
	public void setDtMedicamento(DataTable dtMedicamento) {
		this.dtMedicamento = dtMedicamento;
	}
	public DataTable getDtSuplemento() {
		return dtSuplemento;
	}
	public void setDtSuplemento(DataTable dtSuplemento) {
		this.dtSuplemento = dtSuplemento;
	}
	public String getSuplementos() {
		return suplementos;
	}
	public void setSuplementos(String suplementos) {
		this.suplementos = suplementos;
	}
	public DataTable getDtHistoriaClinica() {
		return dtHistoriaClinica;
	}
	public void setDtHistoriaClinica(DataTable dtHistoriaClinica) {
		this.dtHistoriaClinica = dtHistoriaClinica;
	}
	
}
