package ec.edu.epn.nutricion.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import ec.edu.epn.nutricion.entities.Alimento;
import ec.edu.epn.nutricion.entities.AntecedenteAlimentario;
import ec.edu.epn.nutricion.entities.Antropometria;
import ec.edu.epn.nutricion.entities.Cirugia;
import ec.edu.epn.nutricion.entities.DatosMedico;
import ec.edu.epn.nutricion.entities.HistoriaClinica;
import ec.edu.epn.nutricion.entities.IntoleranciaAlergica;
import ec.edu.epn.nutricion.entities.Medicamento;
import ec.edu.epn.nutricion.entities.Paciente;
import ec.edu.epn.nutricion.entities.PatologiaAsociada;
import ec.edu.epn.nutricion.entities.ProblemaGastrointestinal;
import ec.edu.epn.nutricion.entities.SuplementoNutricional;
import ec.edu.epn.nutricion.model.servicio.ServicioGenerico;
import ec.edu.epn.nutricion.model.servicio.ServicioPaciente;
import ec.edu.epn.nutricion.utils.FuncionesUtiles;

@ManagedBean
@ViewScoped
public class HistoriaClinicaBean {
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
	@EJB
	private ServicioPaciente servicioPaciente;
	private Paciente paciente;
	private Alimento alimento;
	private AntecedenteAlimentario antecedenteAlimentario;
	private Antropometria antropometria;
	private Cirugia cirugia;
	private DatosMedico datosMedicos;
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
	private List<HistoriaClinica> listaHistoriaClinica;
	private List<IntoleranciaAlergica> listaIntoleranciaAlergia;
	private List<Medicamento> listaMedicamento;
	private List<PatologiaAsociada> listaPatologiaAsociadaFamiliar;
	private List<PatologiaAsociada> listaPatologiaAsociadaPaciente;
	private List<ProblemaGastrointestinal> listaProblemaGastrointestinal;
	private List<SuplementoNutricional> listaSuplementoNutricional;

	private boolean nuevo = false;
	private boolean consulta = false;
	private boolean listado = true;
	private String ejercicio = "false";
	private String entrecomidas = "false";
	private String cirugias = "false";
	private String suplementos = "false";
	private String tabaco = "";
	private String alcohol = "";
	private String bebidasAzucaradas = "";
	private DataTable dtPatologiaFamiliar;
	private DataTable dtPatologiaPaciente;
	private DataTable dtAlimento;
	private DataTable dtAlimentoNoPreferido;
	private DataTable dtRefrigerio;
	private DataTable dtCirugia;
	private DataTable dtMedicamento;
	private DataTable dtSuplemento;
	private DataTable dtHistoriaClinica;
	private DataTable dtProblemasGatrointestinales;
	private DataTable dtIntoleranciaAlergica;
	private DataTable dtPacientes;
	@PostConstruct
	public void init() {
		paciente=new Paciente();
		limpiar();
		listaPaciente = servicioPaciente.obtenerListaCombo("Paciente");
		System.out.println("hfgdxg");
	}
	public String crear() {
		try {
			if (paciente.getIdPaciente() ==0) {
				nuevo = true;
				listado = false;
				paciente = new Paciente();
				limpiar();
				System.out.println(paciente);
			} else {
				limpiar();
				listado = false;
				nuevo = true;
				calcular();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/historiaClinica/nuevaHistoriaClinica.jsf";
	}
	public String consultarHistorias() {
		if (paciente != null) {
			paciente = servicioPaciente.buscarPorId(paciente.getIdPaciente());
			listado = false;
			cargar();
		} else {
			System.out.println("entro>>>>>>>>>>>>>>>");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un paciente"));
		}
		return null;
	}
	private void cargar() {
		// historiaClinica=paciente.getHistoriaClinicas();
		for (PatologiaAsociada pa : paciente.getListaPatologiaAsociadas()) {
			if (pa.getFamiliar().equals("")) {
				listaPatologiaAsociadaPaciente.add(pa);
			} else {
				listaPatologiaAsociadaFamiliar.add(pa);
			}
		}
		antecedenteAlimentario = paciente.getAntecedenteAlimentario();
		for (Alimento al : antecedenteAlimentario.getListaAlimentos()) {
			if ("preferido".equals(al.getDescripcionAlimento()))
				listaAlimento.add(al);
			else if ("no preferido".equals(al.getDescripcionAlimento()))
				listaAlimentoNoPreferido.add(al);
			else if ("refrigerio".equals(al.getDescripcionAlimento()))
				listaRefrigerios.add(al);
		}
		String[] habit = antecedenteAlimentario.getHabitos().split("~");
		tabaco = habit[0];
		alcohol = habit[1];
		bebidasAzucaradas = habit[2];
		listaProblemaGastrointestinal = antecedenteAlimentario.getListaProblemaGastrointestinals();
		listaIntoleranciaAlergia = antecedenteAlimentario.getListaIntoleranciaAlergicas();
		listaCirugia = paciente.getListaCirugias();

	}
	private void limpiar() {
		antropometria = new Antropometria();
		datosMedicos = new DatosMedico();
		antecedenteAlimentario = new AntecedenteAlimentario();
		historiaClinica = new HistoriaClinica();
		listaAlimento = new ArrayList<Alimento>();
		listaAlimentoNoPreferido = new ArrayList<Alimento>();
		listaRefrigerios = new ArrayList<Alimento>();
		listaCirugia = new ArrayList<Cirugia>();
		listaSuplementoNutricional = new ArrayList<SuplementoNutricional>();
		listaMedicamento = new ArrayList<Medicamento>();
		listaPatologiaAsociadaFamiliar = new ArrayList<PatologiaAsociada>();
		listaPatologiaAsociadaPaciente = new ArrayList<PatologiaAsociada>();
		listaProblemaGastrointestinal = new ArrayList<ProblemaGastrointestinal>();
		listaIntoleranciaAlergia = new ArrayList<IntoleranciaAlergica>();
		nuevo = false;
		consulta = false;
		listado = true;
		ejercicio = "false";
		entrecomidas = "false";
		cirugias = "false";
		suplementos = "false";
		tabaco = "";
		alcohol = "";
		bebidasAzucaradas = "";
		// listaPatologiaAsociada =
		// servicioPatologiaAsociada.obtenerListaCombo("PatologiaAsociada");
	}
	public String cancelar() {
		nuevo = false;
		consulta = false;
		listado = true;
		return "/pages/historiaClinica/historiaClinica.jsf";
	}
	public String guardar() {
		calcular();
		antecedenteAlimentario.setHabitos("~" + tabaco + "~" + alcohol + "~" + bebidasAzucaradas);
		servicioAntecedenteAlimentario.guardar(antecedenteAlimentario);
		paciente.setAntecedenteAlimentario(antecedenteAlimentario);
		servicioPaciente.insertar(paciente);
		servicioDatosMedico.guardar(datosMedicos);
		historiaClinica.setPaciente(paciente);
		historiaClinica.setDatosMedico(datosMedicos);
		servicioHistoriaClinica.guardar(historiaClinica);

		for (PatologiaAsociada pa : listaPatologiaAsociadaFamiliar) {
			pa.setPaciente(paciente);
			servicioPatologiaAsociada.guardar(pa);
			paciente.addPatologiaAsociada(pa);
		}
		for (PatologiaAsociada pa : listaPatologiaAsociadaPaciente) {
			pa.setPaciente(paciente);
			servicioPatologiaAsociada.guardar(pa);
			paciente.addPatologiaAsociada(pa);
		}

		for (Alimento a : listaAlimento) {
			a.setAntecedentesAlimentario(antecedenteAlimentario);
			servicioAlimento.guardar(a);
			antecedenteAlimentario.addAlimento(a);
		}
		for (Alimento a : listaAlimentoNoPreferido) {
			a.setAntecedentesAlimentario(antecedenteAlimentario);
			servicioAlimento.guardar(a);
			antecedenteAlimentario.addAlimento(a);
		}
		for (Alimento a : listaRefrigerios) {
			a.setAntecedentesAlimentario(antecedenteAlimentario);
			servicioAlimento.guardar(a);
			antecedenteAlimentario.addAlimento(a);
		}
		for (ProblemaGastrointestinal pg : listaProblemaGastrointestinal) {
			pg.setAntecedentesAlimentario(antecedenteAlimentario);
			servicioProblemaGastrointestinal.guardar(pg);
			antecedenteAlimentario.addProblemaGastrointestinal(pg);
		}
		for (IntoleranciaAlergica ia : listaIntoleranciaAlergia) {
			ia.setAntecedentesAlimentario(antecedenteAlimentario);
			servicioIntoleranciaAlergia.guardar(ia);
			antecedenteAlimentario.addIntoleranciaAlergica(ia);
		}
		for (Cirugia c : listaCirugia) {
			c.setPaciente(paciente);
			servicioCirugia.guardar(c);
			paciente.addCirugia(c);
		}
		for (Medicamento m : listaMedicamento) {
			m.setDatosMedico(datosMedicos);
			servicioMedicamento.guardar(m);
			datosMedicos.addMedicamento(m);
		}
		for (SuplementoNutricional sn : listaSuplementoNutricional) {
			sn.setDatosMedico(datosMedicos);
			servicioSuplementoNutricional.guardar(sn);
			datosMedicos.addSuplementoNutricional(sn);
		}
		antropometria.setDatosMedico(datosMedicos);
		servicioAntropometria.guardar(antropometria);
		paciente = null;
		limpiar();
		return null;
	}
	public String calcular() {
		String estado1 = "";
		String estado2 = "";
		String estado3 = "";
		if (paciente.getFechaNacimiento() != null)
			paciente.setEdad(FuncionesUtiles.calcularEdad(paciente.getFechaNacimiento(), new Date()));
		if (antropometria.getTalla() > 0) {
			System.out.println(antropometria.getTalla());
			antropometria
					.setIndiceMasaCorporal(Math.round(antropometria.getPesoActual() / Math.pow((antropometria.getTalla() / 100), 2) * 100.0) / 100.0);
			System.out.println("IMC<<<<<<" + antropometria.getIndiceMasaCorporal());
			if (antropometria.getConstitucionCorporal().equals("Pequena")) {
				antropometria.setPesoIdeal((antropometria.getTalla() - 100) - ((antropometria.getTalla() - 100) * 0.1));
			} else if (antropometria.getConstitucionCorporal().equals("Mediana")) {
				antropometria.setPesoIdeal((antropometria.getTalla() - 100));
			} else if (antropometria.getConstitucionCorporal().equals("Gruesa")) {
				antropometria.setPesoIdeal((antropometria.getTalla() - 100) + ((antropometria.getTalla() - 100) * 0.1));
			}
			estado1 = "Paciente de edad: " + paciente.getEdad() + ", de contextura: " + antropometria.getConstitucionCorporal()
					+ ", debe tener un peso optimo de: " + antropometria.getPesoIdeal();
			antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
		}
		if (antropometria.getPliegueTricipital() > 0) {
			double reservaGrasa = 0;
			if ("Masculino".equals(paciente.getSexo())) {
				reservaGrasa = (antropometria.getPliegueTricipital() * 100) / 12.5;
			} else if ("Femenino".equals(paciente.getSexo())) {
				reservaGrasa = (antropometria.getPliegueTricipital() * 100) / 16.5;
			}
			System.out.println(paciente.getSexo());
			System.out.println("reservaGrasaZZZZZ" + reservaGrasa);
			if (reservaGrasa < 60) {
				estado2 = "\nEstado de reservas grasas: DEPLECION SEVERA (" + reservaGrasa + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			} else if (reservaGrasa >= 60 && reservaGrasa <= 90) {
				estado2 = "\nEstado de reservas grasas: DEPLECION MODERADA (" + reservaGrasa + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			} else if (reservaGrasa > 90 && reservaGrasa <= 110) {
				estado2 = "\nEstado de reservas grasas:DEPLECION LEVE (" + reservaGrasa + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			} else if (reservaGrasa > 110) {
				estado2 = "\nEstado de reservas grasas: RESERVAS GRASAS ELEVADAS (" + reservaGrasa + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			}
		}
		if (antropometria.getPliegueTricipital() > 0 && antropometria.getPerimetroBraquial() > 0) {
			double indicadorDeMasaProteicaMuscular = 0;
			indicadorDeMasaProteicaMuscular = Math.abs(antropometria.getPerimetroBraquial() - (Math.PI * antropometria.getPliegueTricipital()));
			antropometria.setReservaProteica(Math.round(indicadorDeMasaProteicaMuscular * 100.0) / 100.0);
			indicadorDeMasaProteicaMuscular = 0;
			if ("Masculino".equals(paciente.getSexo())) {
				indicadorDeMasaProteicaMuscular = (antropometria.getReservaProteica() * 100) / 25.3;

			} else if ("Femenino".equals(paciente.getSexo())) {
				indicadorDeMasaProteicaMuscular = (antropometria.getReservaProteica() * 100) / 23.2;
			}
			if (indicadorDeMasaProteicaMuscular > 90) {
				estado3 = "\nEstado de reservas de proteina: Normal(" + indicadorDeMasaProteicaMuscular + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			} else if (indicadorDeMasaProteicaMuscular >= 80 && indicadorDeMasaProteicaMuscular <= 90) {
				estado3 = "\nEstado de reservas de proteina: DESGASTE LEVE (" + indicadorDeMasaProteicaMuscular + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			} else if (indicadorDeMasaProteicaMuscular >= 60 && indicadorDeMasaProteicaMuscular <= 79) {
				estado3 = "\nEstado de reservas de proteina: DESGASTE MODERADO (" + indicadorDeMasaProteicaMuscular + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			} else if (indicadorDeMasaProteicaMuscular < 60) {
				estado3 = "\nEstado de reservas de proteina: DESGASTE SEVERO (" + indicadorDeMasaProteicaMuscular + "%)\n";
				antropometria.setObservacionPliegue(estado1 + estado2 + estado3);
			}
		}
		return null;
	}
	public String eliminardtIntoleranciaAlergica() {
		IntoleranciaAlergica su = (IntoleranciaAlergica) dtIntoleranciaAlergica.getRowData();
		listaIntoleranciaAlergia.remove(su);
		return null;
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
	public String eliminarProblemaGastrointestinal() {
		ProblemaGastrointestinal problem = (ProblemaGastrointestinal) dtProblemasGatrointestinales.getRowData();
		listaProblemaGastrointestinal.remove(problem);
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
	public String agregarIntoleranciaAlergica() {
		IntoleranciaAlergica into = new IntoleranciaAlergica();
		listaIntoleranciaAlergia.add(into);
		return null;
	}
	public String agregarPatologiaFamiliar() {
		PatologiaAsociada patologiaAsoc = new PatologiaAsociada();
		listaPatologiaAsociadaFamiliar.add(patologiaAsoc);
		return null;
	}
	public String agregarPatologiaPaciente() {
		PatologiaAsociada patologiaAsoc = new PatologiaAsociada();
		listaPatologiaAsociadaPaciente.add(patologiaAsoc);
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
	public String agregarProblemasGastroinstestinales() {
		ProblemaGastrointestinal problem = new ProblemaGastrointestinal();
		listaProblemaGastrointestinal.add(problem);
		return null;
	}
	public String agregarAlimentoNoPreferido() {
		Alimento alimen = new Alimento();
		alimen.setDescripcionAlimento("no preferido");
		listaAlimentoNoPreferido.add(alimen);
		return null;
	}

	public String eliminarPatologiaFamiliar() {
		PatologiaAsociada pt = (PatologiaAsociada) dtPatologiaFamiliar.getRowData();
		listaPatologiaAsociadaFamiliar.remove(pt);
		return null;
	}
	public String eliminarPatologiaPaciente() {
		PatologiaAsociada pt = (PatologiaAsociada) dtPatologiaPaciente.getRowData();
		listaPatologiaAsociadaPaciente.remove(pt);
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

	public List<PatologiaAsociada> getListaPatologiaAsociadaFamiliar() {
		return listaPatologiaAsociadaFamiliar;
	}
	public void setListaPatologiaAsociadaFamiliar(List<PatologiaAsociada> listaPatologiaAsociadaFamiliar) {
		this.listaPatologiaAsociadaFamiliar = listaPatologiaAsociadaFamiliar;
	}
	public List<PatologiaAsociada> getListaPatologiaAsociadaPaciente() {
		return listaPatologiaAsociadaPaciente;
	}
	public void setListaPatologiaAsociadaPaciente(List<PatologiaAsociada> listaPatologiaAsociadaPaciente) {
		this.listaPatologiaAsociadaPaciente = listaPatologiaAsociadaPaciente;
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
	public DataTable getDtPatologiaFamiliar() {
		return dtPatologiaFamiliar;
	}
	public void setDtPatologiaFamiliar(DataTable dtPatologiaFamiliar) {
		this.dtPatologiaFamiliar = dtPatologiaFamiliar;
	}
	public DataTable getDtPatologiaPaciente() {
		return dtPatologiaPaciente;
	}
	public void setDtPatologiaPaciente(DataTable dtPatologiaPaciente) {
		this.dtPatologiaPaciente = dtPatologiaPaciente;
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
	public DataTable getDtProblemasGatrointestinales() {
		return dtProblemasGatrointestinales;
	}
	public void setDtProblemasGatrointestinales(DataTable dtProblemasGatrointestinales) {
		this.dtProblemasGatrointestinales = dtProblemasGatrointestinales;
	}
	public DataTable getDtIntoleranciaAlergica() {
		return dtIntoleranciaAlergica;
	}
	public void setDtIntoleranciaAlergica(DataTable dtIntoleranciaAlergica) {
		this.dtIntoleranciaAlergica = dtIntoleranciaAlergica;
	}
	public DataTable getDtPacientes() {
		return dtPacientes;
	}
	public void setDtPacientes(DataTable dtPacientes) {
		this.dtPacientes = dtPacientes;
	}
	public boolean isConsulta() {
		return consulta;
	}
	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}
	public boolean isListado() {
		return listado;
	}
	public void setListado(boolean listado) {
		this.listado = listado;
	}
}
